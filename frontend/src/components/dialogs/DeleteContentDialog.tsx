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
import { deleteContent } from '@/api/contentApi';

interface DeleteContentDialogProps {
  open: boolean;
  onOpenChange: (open: boolean) => void;
  contentId: number | null;
  onDeleteSuccess: () => void;
}

export function DeleteContentDialog({ 
  open, 
  onOpenChange, 
  contentId,
  onDeleteSuccess 
}: DeleteContentDialogProps) {
  const [isDeleting, setIsDeleting] = useState(false);
  
  const handleDelete = async () => {
    if (!contentId) return;
    
    setIsDeleting(true);
    try {
      await deleteContent(contentId);
      onOpenChange(false);
      onDeleteSuccess();
    } catch (error) {
      console.error('Failed to delete content:', error);
    } finally {
      setIsDeleting(false);
    }
  };
  
  return (
    <Dialog open={open} onOpenChange={onOpenChange}>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>Delete Content</DialogTitle>
          <DialogDescription>
            Are you sure you want to delete this content? This action cannot be undone.
          </DialogDescription>
        </DialogHeader>
        <DialogFooter>
          <Button variant="outline" onClick={() => onOpenChange(false)} disabled={isDeleting}>
            Cancel
          </Button>
          <Button 
            variant="destructive" 
            onClick={handleDelete} 
            disabled={isDeleting}
          >
            {isDeleting ? "Deleting..." : "Delete"}
          </Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
}