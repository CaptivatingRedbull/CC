import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import { describe, it, expect, vi, beforeEach } from 'vitest';
import { LoginDialog } from './LoginDialog';
import * as authApi from '@/api/authApi';

vi.mock('@/api/authApi', () => ({
  login: vi.fn(),
}));

const mockOnOpenChange = vi.fn();
const mockOnLoginSuccess = vi.fn();

describe('LoginDialog', () => {
  beforeEach(() => {
    vi.clearAllMocks();
    localStorage.clear();
  });

  it('renders correctly when open', () => {
    render(<LoginDialog open={true} onOpenChange={mockOnOpenChange} onLoginSuccess={mockOnLoginSuccess} />);
    expect(screen.getByText('Admin Login')).toBeInTheDocument();
    expect(screen.getByLabelText('Username')).toBeInTheDocument();
    expect(screen.getByLabelText('Password')).toBeInTheDocument();
  });

  it('shows error if fields are empty on submit', async () => {
    render(<LoginDialog open={true} onOpenChange={mockOnOpenChange} onLoginSuccess={mockOnLoginSuccess} />);
    fireEvent.click(screen.getByText('Login'));
    expect(await screen.findByText('Username and password are required')).toBeInTheDocument();
  });

  it('calls login API with correct credentials and handles success', async () => {
    const token = 'fake-jwt-token';
    vi.mocked(authApi.login).mockResolvedValueOnce({ token, username: 'admin' });

    render(<LoginDialog open={true} onOpenChange={mockOnOpenChange} onLoginSuccess={mockOnLoginSuccess} />);
    fireEvent.change(screen.getByLabelText('Username'), { target: { value: 'admin' } });
    fireEvent.change(screen.getByLabelText('Password'), { target: { value: 'password' } });
    fireEvent.click(screen.getByText('Login'));

    await waitFor(() => {
      expect(authApi.login).toHaveBeenCalledWith({ username: 'admin', password: 'password' });
    });

    expect(localStorage.getItem('token')).toBe(token);
    expect(mockOnLoginSuccess).toHaveBeenCalledTimes(1);
    expect(mockOnOpenChange).toHaveBeenCalledWith(false);
  });

  it('shows error message on failed login', async () => {
    const errorMessage = 'Login failed';
    vi.mocked(authApi.login).mockRejectedValueOnce(new Error(errorMessage));

    render(<LoginDialog open={true} onOpenChange={mockOnOpenChange} onLoginSuccess={mockOnLoginSuccess} />);
    fireEvent.change(screen.getByLabelText('Username'), { target: { value: 'admin' } });
    fireEvent.change(screen.getByLabelText('Password'), { target: { value: 'wrongpassword' } });
    fireEvent.click(screen.getByText('Login'));

    expect(await screen.findByText(errorMessage)).toBeInTheDocument();
    expect(localStorage.getItem('token')).toBeNull();
    expect(mockOnLoginSuccess).not.toHaveBeenCalled();
  });
});