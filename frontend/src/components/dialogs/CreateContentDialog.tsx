import { useState } from 'react';
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
import { createContent } from '@/api/contentApi';

interface CreateContentDialogProps {
    open: boolean;
    onOpenChange: (open: boolean) => void;
    onSuccess: () => void;
}

export function CreateContentDialog({ open, onOpenChange, onSuccess }: CreateContentDialogProps) {
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [year, setYear] = useState<string>('');
    const [kind, setKind] = useState<Kind | ''>('');
    const [error, setError] = useState('');
    const [isLoading, setIsLoading] = useState(false);

    const handleSubmit = async () => {
        // Form validation
        if (!title) {
            setError('Title is required');
            return;
        }
        if (!description) {
            setError('Description is required');
            return;
        }
        if (!year) {
            setError('Release Year is required');
            return;
        }
        if (!kind) {
            setError('Type is required');
            return;
        }

        const yearNum = parseInt(year);
        if (isNaN(yearNum)) {
            setError('Year must be a valid number');
            return;
        }

        setIsLoading(true);
        setError('');

        try {
            await createContent({
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

            // Clear form
            setTitle('');
            setDescription('');
            setYear('');
            setKind('');

        } catch (error) {
            setError(error instanceof Error ? error.message : 'Failed to create content');
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <Dialog open={open} onOpenChange={onOpenChange}>
            <DialogContent className="sm:max-w-[525px]">
                <DialogHeader>
                    <DialogTitle>Create New Content</DialogTitle>
                    <DialogDescription>
                        Add a new movie or series to the collection.
                    </DialogDescription>
                </DialogHeader>

                <div className="space-y-4 py-2">
                    {error && (
                        <div className="text-sm font-medium text-red-500">{error}</div>
                    )}

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
                </div>

                <DialogFooter>
                    <Button variant="outline" onClick={() => onOpenChange(false)} disabled={isLoading}>
                        Cancel
                    </Button>
                    <Button onClick={handleSubmit} disabled={isLoading}>
                        {isLoading ? "Creating..." : "Create Content"}
                    </Button>
                </DialogFooter>
            </DialogContent>
        </Dialog>
    );
}