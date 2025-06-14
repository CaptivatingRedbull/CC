import { render, screen, waitFor, within } from '@testing-library/react';
import { describe, it, expect, vi, afterEach } from 'vitest';
import { AdminPage } from './adminPage';

// Mock the ContentPage to isolate AdminPage logic
vi.mock('./contentPage', () => ({
  ContentPage: ({ adminExtension, headerActions }: {
      adminExtension: (itemId: number) => React.ReactNode;
      headerActions: React.ReactNode;
  }) => (
    <div>
      <div data-testid="header-actions">{headerActions}</div>
      <div data-testid="admin-extension">{adminExtension(1)}</div>
    </div>
  ),
}));

// Mock dialog components to avoid rendering them fully
vi.mock('@/components/dialogs/LoginDialog', () => ({ LoginDialog: () => <div data-testid="login-dialog" /> }));
vi.mock('@/components/dialogs/DeleteContentDialog', () => ({ DeleteContentDialog: () => <div data-testid="delete-dialog" /> }));
vi.mock('@/components/dialogs/UpdateContentDialog', () => ({ UpdateContentDialog: () => <div data-testid="update-dialog" /> }));
vi.mock('@/components/dialogs/CreateUserDialog', () => ({ CreateUserDialog: () => <div data-testid="create-user-dialog" /> }));
vi.mock('@/components/dialogs/CreateContentDialog', () => ({ CreateContentDialog: () => <div data-testid="create-content-dialog" /> }));


describe('AdminPage', () => {
  afterEach(() => {
    localStorage.clear();
  });

  it('renders login button when not authenticated', () => {
    render(<AdminPage />);
    const headerActions = screen.getByTestId('header-actions');
    expect(within(headerActions).getByText('Login')).toBeInTheDocument();
  });

  it('does not render admin extensions when not authenticated', () => {
    render(<AdminPage />);
    const adminExtension = screen.getByTestId('admin-extension');
    expect(adminExtension).toBeEmptyDOMElement();
  });


  it('renders admin action buttons when authenticated', async () => {
    localStorage.setItem('token', 'fake-token');
    render(<AdminPage />);

    // Wait for the useEffect to run and update state
    await waitFor(() => {
        const headerActions = screen.getByTestId('header-actions');
        expect(within(headerActions).getByText('User')).toBeInTheDocument();
        expect(within(headerActions).getByText('Content')).toBeInTheDocument();
    });
  });

    it('renders admin card extensions when authenticated', async () => {
        localStorage.setItem('token', 'fake-token');
        render(<AdminPage />);

        await waitFor(() => {
             const adminExtension = screen.getByTestId('admin-extension');
             expect(within(adminExtension).getAllByRole('button').length).toBe(2);
        });
    });
});