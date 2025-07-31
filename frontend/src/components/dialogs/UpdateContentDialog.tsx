import { useState, useEffect } from 'react';
import {
    Dialog,
    DialogContent,
    DialogHeader,
    DialogTitle,
    DialogDescription,
    DialogFooter
} from '@/components/ui/dialog';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Textarea } from '@/components/ui/textarea';
import {
    Select,
    SelectContent,
    SelectItem,
    SelectTrigger,
    SelectValue
} from '@/components/ui/select';
import type { Kind } from '@/utils/types';
import { updateContent, getContentById } from '@/api/contentApi';

interface UpdateContentDialogProps {
    open: boolean;
    onOpenChange: (open: boolean) => void;
    contentId: number | null;
    onSuccess: () => void;
}

export function UpdateContentDialog({ open, onOpenChange, contentId, onSuccess }: UpdateContentDialogProps) {
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [year, setYear] = useState<string>('');
    const [kind, setKind] = useState<Kind | ''>('');
    const [error, setError] = useState('');
    const [isLoading, setIsLoading] = useState(false);
    const [fetchingContent, setFetchingContent] = useState(false);

    useEffect(() => {
        if (open && contentId) {
            const fetchContentDetails = async () => {
                setFetchingContent(true);
                try {
                    const content = await getContentById(contentId);
                    setTitle(content.title);
                    setDescription(content.description);
                    setYear(content.year.toString());
                    setKind(content.kind);
                } catch (error) {
                    setError('Failed to fetch content details');
                    console.error(error);
                } finally {
                    setFetchingContent(false);
                }
            };
            
            fetchContentDetails();
        }
    }, [open, contentId]);

    const handleSubmit = async () => {
        if (!contentId) return;
        
        // Form validation
        if (!title || !description || !year || !kind) {
            setError('All fields are required');
            return;
        }

        // Validate year is a number
        const yearNum = parseInt(year);
        if (isNaN(yearNum)) {
            setError('Year must be a valid number');
            return;
        }

        setIsLoading(true);
        setError('');

        try {
            await updateContent(contentId, {
                id: contentId,
                title,
                description,
                year: yearNum,
                kind: kind as Kind,
                upVote: 0, 
                downVote: 0,
                score: 0,
            });

            onSuccess();
            onOpenChange(false);
            
        } catch (error) {
            setError(error instanceof Error ? error.message : 'Failed to update content');
        } finally {
            setIsLoading(false);
        }
    };

    // Reset form when dialog closes
    useEffect(() => {
        if (!open) {
            setTitle('');
            setDescription('');
            setYear('');
            setKind('');
            setError('');
        }
    }, [open]);

    return (
        <Dialog open={open} onOpenChange={onOpenChange}>
            <DialogContent className="sm:max-w-[525px]">
                <DialogHeader>
                    <DialogTitle>Update Content</DialogTitle>
                    <DialogDescription>
                        Edit the movie or series details.
                    </DialogDescription>
                </DialogHeader>

                <div className="space-y-4 py-2">
                    {error && (
                        <div className="text-sm font-medium text-red-500">{error}</div>
                    )}

                    {fetchingContent ? (
                        <div className="py-4 text-center">Loading content details...</div>
                    ) : (
                        <>
                            <div className="space-y-2">
                                <Label htmlFor="title">Title</Label>
                                <Input
                                    id="title"
                                    value={title}
                                    onChange={(e) => setTitle(e.target.value)}
                                    disabled={isLoading}
                                />
                            </div>

                            <div className="space-y-2">
                                <Label htmlFor="description">Description</Label>
                                <Textarea
                                    id="description"
                                    value={description}
                                    onChange={(e) => setDescription(e.target.value)}
                                    disabled={isLoading}
                                    rows={3}
                                />
                            </div>

                            <div className="grid grid-cols-2 gap-4">
                                <div className="space-y-2">
                                    <Label htmlFor="year">Release Year</Label>
                                    <Input
                                        id="year"
                                        type="number"
                                        value={year}
                                        onChange={(e) => setYear(e.target.value)}
                                        disabled={isLoading}
                                    />
                                </div>

                                <div className="space-y-2">
                                    <Label htmlFor="kind">Type</Label>
                                    <Select
                                        value={kind}
                                        onValueChange={(value) => setKind(value as Kind)}
                                        disabled={isLoading}
                                    >
                                        <SelectTrigger id="kind">
                                            <SelectValue placeholder="Select type" />
                                        </SelectTrigger>
                                        <SelectContent>
                                            <SelectItem value="MOVIE">Movie</SelectItem>
                                            <SelectItem value="SERIES">Series</SelectItem>
                                        </SelectContent>
                                    </Select>
                                </div>
                            </div>
                        </>
                    )}
                </div>

                <DialogFooter>
                    <Button variant="outline" onClick={() => onOpenChange(false)} disabled={isLoading || fetchingContent}>
                        Cancel
                    </Button>
                    <Button onClick={handleSubmit} disabled={isLoading || fetchingContent}>
                        {isLoading ? "Updating..." : "Update Content"}
                    </Button>
                </DialogFooter>
            </DialogContent>
        </Dialog>
    );
}