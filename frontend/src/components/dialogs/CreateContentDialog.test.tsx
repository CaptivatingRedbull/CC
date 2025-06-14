import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { describe, it, expect, vi, beforeEach } from 'vitest';
import { CreateContentDialog } from './CreateContentDialog';
import * as contentApi from '@/api/contentApi';

vi.mock('@/api/contentApi', () => ({
  createContent: vi.fn(),
}));

const mockOnOpenChange = vi.fn();
const mockOnSuccess = vi.fn();

describe('CreateContentDialog', () => {
  beforeEach(() => {
    vi.clearAllMocks();
  });

  it('renders the dialog when open is true', () => {
    render(<CreateContentDialog open={true} onOpenChange={mockOnOpenChange} onSuccess={mockOnSuccess} />);
    expect(screen.getByRole('dialog')).toBeInTheDocument();
  });

  it('shows the first specific error message if fields are not filled', async () => {
    const user = userEvent.setup();
    render(<CreateContentDialog open={true} onOpenChange={mockOnOpenChange} onSuccess={mockOnSuccess} />);
    
    await user.click(screen.getByRole('button', { name: /create content/i }));
    
    expect(await screen.findByText('Title is required')).toBeInTheDocument();
  });
  
  it('shows a required error when year is invalid because type="number" blocks non-numeric input', async () => {
    const user = userEvent.setup();
    render(<CreateContentDialog open={true} onOpenChange={mockOnOpenChange} onSuccess={mockOnSuccess} />);
    
    await user.type(screen.getByLabelText('Title'), 'New Movie');
    await user.type(screen.getByLabelText('Description'), 'A great movie.');
    
    await user.type(screen.getByLabelText('Release Year'), 'abcd'); 
    
    await user.click(screen.getByRole('combobox'));
    await user.click(await screen.findByText('Movie'));
    await user.click(screen.getByRole('button', { name: /create content/i }));
    
    expect(await screen.findByText('Release Year is required')).toBeInTheDocument();
  });

  it('calls the createContent API on successful submission', async () => {
    const user = userEvent.setup();
    vi.mocked(contentApi.createContent).mockResolvedValueOnce({} as any);
    render(<CreateContentDialog open={true} onOpenChange={mockOnOpenChange} onSuccess={mockOnSuccess} />);
    
    await user.type(screen.getByLabelText('Title'), 'New Movie');
    await user.type(screen.getByLabelText('Description'), 'A great movie.');
    await user.type(screen.getByLabelText('Release Year'), '2023');
    await user.click(screen.getByRole('combobox'));
    await user.click(await screen.findByText('Movie'));
    await user.click(screen.getByRole('button', { name: /create content/i }));

    await waitFor(() => {
      expect(contentApi.createContent).toHaveBeenCalledWith({
        title: 'New Movie',
        description: 'A great movie.',
        year: 2023,
        kind: 'MOVIE',
        upVote: 0,
        downVote: 0,
      });
    });
    expect(mockOnSuccess).toHaveBeenCalled();
  });

  it('displays an error message if the API call fails', async () => {
    const user = userEvent.setup();
    const errorMessage = 'Failed to create content';
    vi.mocked(contentApi.createContent).mockRejectedValueOnce(new Error(errorMessage));
    render(<CreateContentDialog open={true} onOpenChange={mockOnOpenChange} onSuccess={mockOnSuccess} />);

    await user.type(screen.getByLabelText('Title'), 'Test Title');
    await user.type(screen.getByLabelText('Description'), 'Test Description');
    await user.type(screen.getByLabelText('Release Year'), '2024');
    await user.click(screen.getByRole('combobox'));
    await user.click(await screen.findByText('Series'));
    await user.click(screen.getByRole('button', { name: /create content/i }));

    expect(await screen.findByText(errorMessage)).toBeInTheDocument();
  });
});