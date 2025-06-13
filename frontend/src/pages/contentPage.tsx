import { useState, useEffect, useCallback } from 'react';
import {
    Select,
    SelectContent,
    SelectItem,
    SelectTrigger,
    SelectValue,
} from "@/components/ui/select";
import {
    Card,
    CardContent,
    CardFooter,
    CardHeader,
    CardTitle,
    CardDescription,
} from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Separator } from "@/components/ui/separator";
import { Pagination, PaginationContent, PaginationItem, PaginationNext, PaginationPrevious } from "@/components/ui/pagination";
import { ArrowUp, ArrowDown, Calendar, Clapperboard, Tv, SortAsc, SortDesc, LogIn } from 'lucide-react';
import { getAllContents, addUpVote, addDownVote } from '@/api/contentApi';
import type { Content, Kind, Page } from '@/utils/types';

interface ContentPageProps {
    adminExtension?: (itemId: number) => React.ReactNode; //used to inject admin actions like delete
    refreshTrigger?: number;
    onLoginClick?: () => void;
    headerActions?: React.ReactNode;
}

const sortOptions = [
    { value: 'year', label: 'Year' },
    { value: 'title', label: 'Title (A-Z)' },
    { value: 'score', label: 'Votes' },
];

const GetKindIcon = ({ kind, className }: { kind: Kind, className?: string }) => {
    const defaultClassName = "mr-2 h-5 w-5 text-muted-foreground";
    const combinedClassName = `${defaultClassName} ${className || ''}`;
    switch (kind) {
        case "MOVIE": return <Clapperboard className={combinedClassName} />;
        case "SERIES": return <Tv className={combinedClassName} />;
        default: return null;
    }
};

{/* CSS animation for skeleton */ }
const animationStyle = `
  @keyframes pulse {
    50% {
      opacity: .5;
    }
  }
  .animate-pulse {
    animation: pulse 2s cubic-bezier(0.4, 0, 0.6, 1) infinite;
  }
`;

const ContentCardSkeleton = () => (
    <Card className="flex flex-col">
        <CardHeader>
            <CardTitle className="flex items-center">
                {/* Placeholder for Icon */}
                <div className="h-5 w-5 mr-2 rounded-full bg-gray-200 animate-pulse" />
                {/* Placeholder for Title */}
                <div className="h-6 w-3/4 rounded bg-gray-200 animate-pulse" />
            </CardTitle>
            <CardDescription className="pt-2 flex items-center">
                {/* Placeholder for Calendar Icon */}
                <div className="h-4 w-4 mr-2 rounded bg-gray-200 animate-pulse" />
                {/* Placeholder for Year */}
                <div className="h-4 w-1/4 rounded bg-gray-200 animate-pulse" />
            </CardDescription>
        </CardHeader>
        <CardContent className="flex-grow">
            <div className="space-y-2">
                {/* Placeholder for Description */}
                <div className="h-4 w-full rounded bg-gray-200 animate-pulse" />
                <div className="h-4 w-full rounded bg-gray-200 animate-pulse" />
                <div className="h-4 w-5/6 rounded bg-gray-200 animate-pulse" />
            </div>
        </CardContent>
        <CardFooter className="flex justify-end items-center pt-4 border-t">
            {/* Placeholder for Vote Buttons */}
            <div className="h-10 w-28 rounded-lg bg-gray-200 animate-pulse" />
        </CardFooter>
    </Card>
);


export function ContentPage({ adminExtension, refreshTrigger = 0, onLoginClick, headerActions }: ContentPageProps) {
    // State for filters and pagination
    const [kind, setKind] = useState<Kind | undefined>(undefined);
    const [searchTerm, setSearchTerm] = useState<string>('');
    const [sortBy, setSortBy] = useState<string>("year");
    const [sortDirection, setSortDirection] = useState<"desc" | "asc">("desc");
    const [currentPage, setCurrentPage] = useState(0);
    const [pageSize] = useState(10);
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    // State for data
    const [contentPage, setContentPage] = useState<Page<Content> | null>(null);
    const [loading, setLoading] = useState(true);

    // Fetch contents from the API
    const loadContents = useCallback(async () => {
        setLoading(true);
        try {
            const result = await getAllContents(
                currentPage,
                pageSize,
                sortBy,
                sortDirection,
                searchTerm,
                kind
            );
            setContentPage(result);
        } catch (error) {
            console.error('Failed to load contents:', error);
        } finally {
            setLoading(false);
        }
    }, [currentPage, pageSize, sortBy, sortDirection, searchTerm, kind]);

    // Effect to load contents when dependencies change
    useEffect(() => {
        loadContents();
    }, [loadContents, refreshTrigger]);

    useEffect(() => {
        const token = localStorage.getItem('token');
        setIsLoggedIn(!!token);
    }, []);

    // Handlers for UI interactions
    const handleKindChange = (value: string) => {
        const newKind = value === 'all' ? undefined : value as Kind;
        setKind(newKind);
        setCurrentPage(0);
    };

    const handleSearchChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setSearchTerm(event.target.value);
        setCurrentPage(0);
    };

    const handleSortChange = (value: string) => {
        setSortBy(value);
        if (value === 'title') {
            setSortDirection('asc');
        } else {
            setSortDirection('desc');
        }
        setCurrentPage(0);
    };

    const handleSortDirectionChange = () => {
        setSortDirection(prev => (prev === 'asc' ? 'desc' : 'asc'));
        setCurrentPage(0);
    };

    const handlePageChange = (page: number) => {
        setCurrentPage(page);
    };

    const handleVote = async (id: number, voteType: 'up' | 'down') => {
        try {
            if (voteType === 'up') {
                await addUpVote(id);
            } else {
                await addDownVote(id);
            }
            loadContents();
        } catch (error) {
            console.error(`Failed to ${voteType}vote:`, error);
        }
    };


    return (
        <div className="container mx-auto p-4">
            {/* Custom header actions or default login button */}
            {headerActions ? (
                <div className="absolute top-4 right-4">
                    {headerActions}
                </div>
            ) : !isLoggedIn && (
                <div className="absolute top-4 right-4">
                    <Button
                        onClick={(e) => {
                            e.preventDefault(); // Prevent any default navigation
                            if (onLoginClick) {
                                onLoginClick();
                            } else {
                                console.log("No login handler provided"); // For debugging
                            }
                        }}
                        variant="outline"
                        size="sm"
                        className="flex items-center gap-1"
                        type="button" // Explicitly set type to prevent form submission
                    >
                        <LogIn className="h-4 w-4" />
                        <span>Login</span>
                    </Button>
                </div>
            )}

            {/* Inject the CSS animation styles */}
            <style>{animationStyle}</style>

            <header className="mb-6">
                <h1 className="text-4xl font-bold">Browse Content</h1>
                <p className="text-md text-muted-foreground">Discover new movies and series.</p>
            </header>

            {/* Filter and Sort Controls */}
            <div className="flex flex-col md:flex-row justify-between items-center gap-4 mb-6 p-4 border rounded-lg">
                <div className="grid grid-cols-1 sm:grid-cols-2 md:flex md:flex-wrap md:flex-grow gap-4 w-full">
                    <Input
                        type="text"
                        placeholder="Search by title..."
                        value={searchTerm}
                        onChange={handleSearchChange}
                        className="w-full sm:w-auto md:flex-1 min-w-[180px]"
                        disabled={loading}
                    />
                    <Select value={kind || 'all'} onValueChange={handleKindChange} disabled={loading}>
                        <SelectTrigger className="w-full sm:w-auto md:flex-1 min-w-[150px]">
                            <SelectValue placeholder="Select Kind" />
                        </SelectTrigger>
                        <SelectContent>
                            <SelectItem value="all">All Kinds</SelectItem>
                            <SelectItem value={"MOVIE"}>Movie</SelectItem>
                            <SelectItem value={"SERIES"}>Series</SelectItem>
                        </SelectContent>
                    </Select>
                    <div className="flex items-center gap-2">
                        <Select value={sortBy} onValueChange={handleSortChange} disabled={loading}>
                            <SelectTrigger className="w-full sm:w-auto md:flex-1 min-w-[150px]">
                                <SelectValue placeholder="Sort by" />
                            </SelectTrigger>
                            <SelectContent>
                                {sortOptions.map(option => (
                                    <SelectItem key={option.value} value={option.value}>
                                        {option.label}
                                    </SelectItem>
                                ))}
                            </SelectContent>
                        </Select>
                        <Button variant="outline" size="icon" onClick={handleSortDirectionChange} disabled={loading}>
                            {sortDirection === 'asc' ? <SortAsc className="h-4 w-4" /> : <SortDesc className="h-4 w-4" />}
                        </Button>
                    </div>
                </div>
            </div>

            <Separator className="my-6" />

            {/* Content Display */}
            {(loading && !contentPage) ? (
                <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
                    {[...Array(pageSize)].map((_, index) => (
                        <ContentCardSkeleton key={index} />
                    ))}
                </div>
            ) : contentPage && contentPage.content.length > 0 ? (
                <>
                    <div className={`grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6 transition-opacity duration-300 ${loading ? 'opacity-50' : 'opacity-100'}`}>
                        {contentPage.content.map(item => (
                            <Card key={item.id} className="flex flex-col transition-shadow duration-300">
                                <CardHeader>
                                    <CardTitle className="text-xl font-semibold flex items-center">
                                        <GetKindIcon kind={item.kind} />
                                        {item.title}
                                    </CardTitle>
                                    <CardDescription className="text-muted-foreground pt-2 flex items-center">
                                        <Calendar className="mr-2 h-4 w-4" />  {item.year}
                                    </CardDescription>
                                </CardHeader>
                                <CardContent className="flex-grow">
                                    <p className="text-foreground/80 line-clamp-3">{item.description}</p>
                                </CardContent>
                                <CardFooter className="flex justify-evenly items-center pt-4 border-t">
                                    <div className="flex items-center gap-2 border rounded-lg p-1">
                                        <Button variant="ghost" size="icon" className="text-green-600 hover:text-green-700 hover:bg-green-100" onClick={() => handleVote(item.id, 'up')}>
                                            <ArrowUp className="h-5 w-5" />
                                        </Button>
                                        <span className="font-bold text-lg w-10 text-center select-none">
                                            {typeof item.upVote === "number" && typeof item.downVote === "number"
                                                ? item.upVote - item.downVote
                                                : typeof item.upVote === "number"
                                                    ? item.upVote
                                                    : typeof item.downVote === "number"
                                                        ? -item.downVote
                                                        : 0}
                                        </span>
                                        <Button variant="ghost" size="icon" className="text-red-600 hover:text-red-700 hover:bg-red-100" onClick={() => handleVote(item.id, 'down')}>
                                            <ArrowDown className="h-5 w-5" />
                                        </Button>
                                    </div>
                                    {adminExtension && adminExtension(item.id)}
                                </CardFooter>
                            </Card>
                        ))}
                    </div>

                    {/* Pagination Controls */}
                    {contentPage.page.totalPages > 1 && (
                        <div className="mt-8 flex justify-center">
                            <Pagination>
                                <PaginationContent>
                                    <PaginationItem>
                                        <PaginationPrevious
                                            onClick={currentPage === 0 ? undefined : () => handlePageChange(currentPage - 1)}
                                            aria-disabled={currentPage === 0}
                                        />
                                    </PaginationItem>
                                    <PaginationItem>
                                        <span className="px-4 py-2 text-sm text-muted-foreground">
                                            Page {currentPage + 1} of {contentPage.page.totalPages}
                                        </span>
                                    </PaginationItem>
                                    <PaginationItem>
                                        <PaginationNext
                                            onClick={currentPage >= contentPage.page.totalPages - 1 ? undefined : () => handlePageChange(currentPage + 1)}
                                            aria-disabled={currentPage >= contentPage.page.totalPages - 1}
                                        />
                                    </PaginationItem>
                                </PaginationContent>
                            </Pagination>
                        </div>
                    )}
                </>
            ) : (
                <div className="text-center py-10">
                    <p className="text-xl font-semibold text-muted-foreground">No content found.</p>
                    <p className="text-sm text-muted-foreground">Try adjusting your filters.</p>
                </div>
            )}
        </div>
    );
}