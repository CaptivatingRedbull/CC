import { render, screen, fireEvent, waitFor, within } from '@testing-library/react';
import { describe, expect, vi, beforeEach, afterEach, test } from 'vitest';
import { ContentPage } from './contentPage';
import * as contentApi from '@/api/contentApi';
import type { Page, Content } from '@/utils/types';

vi.mock('@/api/contentApi');

const mockContent: Content[] = [
  { id: 1, title: 'Movie A', description: 'Desc A', year: 2021, kind: 'MOVIE', upVote: 10, downVote: 2 },
  { id: 2, title: 'Series B', description: 'Desc B', year: 2022, kind: 'SERIES', upVote: 20, downVote: 5 },
];

const mockPage: Page<Content> = {
  content: mockContent,
  page: { totalPages: 2, totalElements: 20, size: 10, number: 0 },
};

describe('ContentPage', () => {
  beforeEach(() => {
    vi.mocked(contentApi.getAllContents).mockResolvedValue(mockPage);
    vi.mocked(contentApi.addUpVote).mockResolvedValue(undefined);
    vi.mocked(contentApi.addDownVote).mockResolvedValue(undefined);
  });

  afterEach(() => {
    vi.clearAllMocks();
  });

  test('fetches and displays content', async () => {
    render(<ContentPage />);
    expect(await screen.findByText('Movie A')).toBeInTheDocument();
    expect(screen.getByText('Series B')).toBeInTheDocument();
  });

  test('filters content by search term', async () => {
    render(<ContentPage />);
    const searchInput = screen.getByPlaceholderText('Search by title...');
    fireEvent.change(searchInput, { target: { value: 'Movie' } });
    await waitFor(() => {
      expect(contentApi.getAllContents).toHaveBeenLastCalledWith(0, 10, 'year', 'desc', 'Movie', undefined);
    });
  });

  test('handles upvoting', async () => {
    render(<ContentPage />);
    
    const cards = await screen.findAllByTestId('content-card');
    expect(cards.length).toBeGreaterThan(0);

    const firstCard = cards[0];
    
    const upvoteButton = within(firstCard).getAllByRole('button')[0];
    fireEvent.click(upvoteButton);

    await waitFor(() => {
      expect(contentApi.addUpVote).toHaveBeenCalledWith(mockContent[0].id);
    });
  });

  test('handles pagination', async () => {
    render(<ContentPage />);
    await screen.findByText('Movie A');

    const nextButton = screen.getByText('Next');
    fireEvent.click(nextButton);

    await waitFor(() => {
      expect(contentApi.getAllContents).toHaveBeenLastCalledWith(1, 10, 'year', 'desc', '', undefined);
    });
  });
  
  test('displays a message when no content is found', async () => {
    vi.mocked(contentApi.getAllContents).mockResolvedValue({
      content: [],
      page: { totalPages: 0, totalElements: 0, size: 10, number: 0 },
    });
    render(<ContentPage />);
    expect(await screen.findByText('No content found.')).toBeInTheDocument();
  });
});