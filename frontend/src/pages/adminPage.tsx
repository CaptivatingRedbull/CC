import { useState, useEffect } from 'react';
import { ContentPage } from './contentPage';
import { Button } from '@/components/ui/button';
import { Trash2, LogIn, UserPlus, FilePlus, Pencil } from 'lucide-react';
import { DeleteContentDialog } from '@/components/dialogs/DeleteContentDialog';
import { LoginDialog } from '@/components/dialogs/LoginDialog';
import { CreateUserDialog } from '@/components/dialogs/CreateUserDialog';
import { CreateContentDialog } from '@/components/dialogs/CreateContentDialog';
import { UpdateContentDialog } from '@/components/dialogs/UpdateContentDialog'; 

export function AdminPage() {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [refreshTrigger, setRefreshTrigger] = useState(0);
    const [selectedItemId, setSelectedItemId] = useState<number | null>(null);
    const [deleteDialogOpen, setDeleteDialogOpen] = useState(false);
    const [updateDialogOpen, setUpdateDialogOpen] = useState(false); 
    const [loginDialogOpen, setLoginDialogOpen] = useState(false);
    const [createUserDialogOpen, setCreateUserDialogOpen] = useState(false);
    const [createContentDialogOpen, setCreateContentDialogOpen] = useState(false);

    useEffect(() => {
        const token = localStorage.getItem('token');
        setIsAuthenticated(!!token);
    }, []);

    // Admin extension component to be injected into ContentPage
    const renderCardFooterExtension = (itemId: number) => {
        if (!isAuthenticated) return null;

        return (
            <div className="flex">
                {/* Edit Button */}
                <Button
                    variant="ghost"
                    size="icon"
                    className="ml-1 text-blue-600 hover:text-blue-700 hover:bg-blue-100"
                    onClick={(e) => {
                        e.stopPropagation();
                        setSelectedItemId(itemId);
                        setUpdateDialogOpen(true);
                    }}
                >
                    <Pencil className="h-5 w-5" />
                </Button>
                
                {/* Delete Button */}
                <Button
                    variant="ghost"
                    size="icon"
                    className="ml-1 text-gray-600 hover:text-red-700 hover:bg-red-100"
                    onClick={(e) => {
                        e.stopPropagation();
                        setSelectedItemId(itemId);
                        setDeleteDialogOpen(true);
                    }}
                >
                    <Trash2 className="h-5 w-5" />
                </Button>
            </div>
        );
    };

    // Handle successful login
    const handleLoginSuccess = () => {
        setIsAuthenticated(true);
    };

    // Handle content refresh after changes
    const refreshContent = () => {
        setRefreshTrigger(prev => prev + 1);
    };

    // Render admin actions based on authentication state
    const renderAdminActions = () => {
        if (isAuthenticated) {
            return (
                <div className="flex gap-2">
                    <Button
                        variant="outline"
                        size="sm"
                        className="flex items-center gap-1"
                        onClick={(e) => {
                            e.preventDefault();
                            setCreateUserDialogOpen(true);
                        }}
                        type="button"
                    >
                        <UserPlus className="h-4 w-4" />
                        <span>User</span>
                    </Button>
                    <Button
                        variant="outline"
                        size="sm"
                        className="flex items-center gap-1"
                        onClick={(e) => {
                            e.preventDefault();
                            setCreateContentDialogOpen(true);
                        }}
                        type="button"
                    >
                        <FilePlus className="h-4 w-4" />
                        <span>Content</span>
                    </Button>
                </div>
            );
        } else {
            return (
                <Button
                    onClick={(e) => {
                        e.preventDefault();
                        e.stopPropagation();
                        setLoginDialogOpen(true);
                    }}
                    type="button"
                    variant="outline"
                    size="sm"
                    className="flex items-center gap-1"
                >
                    <LogIn className="h-4 w-4" />
                    <span>Login</span>
                </Button>
            );
        }
    };

    return (
        <>
            {/* Render Content Page with admin extensions */}
            <ContentPage
                adminExtension={renderCardFooterExtension}
                refreshTrigger={refreshTrigger}
                headerActions={renderAdminActions()}
            />

            {/* Dialogs */}
            <LoginDialog
                open={loginDialogOpen}
                onOpenChange={setLoginDialogOpen}
                onLoginSuccess={handleLoginSuccess}
            />

            <DeleteContentDialog
                open={deleteDialogOpen}
                onOpenChange={setDeleteDialogOpen}
                contentId={selectedItemId}
                onDeleteSuccess={refreshContent}
            />

            <UpdateContentDialog
                open={updateDialogOpen}
                onOpenChange={setUpdateDialogOpen}
                contentId={selectedItemId}
                onSuccess={refreshContent}
            />

            <CreateUserDialog
                open={createUserDialogOpen}
                onOpenChange={setCreateUserDialogOpen}
                onSuccess={refreshContent}
            />

            <CreateContentDialog
                open={createContentDialogOpen}
                onOpenChange={setCreateContentDialogOpen}
                onSuccess={refreshContent}
            />
        </>
    );
}