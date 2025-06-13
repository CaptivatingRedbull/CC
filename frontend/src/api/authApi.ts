import api from './api';
import type { AuthRequest, AuthResponse, RegisterRequest } from '@/utils/types';

/**
 * Authenticates a user and returns a token and username.
 * @param credentials - The user's login credentials.
 * @returns A promise that resolves to an object containing the token and username.
 */
export const login = (credentials: AuthRequest): Promise<AuthResponse> => {
  return api.post('/auth/authenticate', credentials);
};

/**
 * Registers a new user.
 * @param userData - The registration data for the new user.
 * @returns A promise that resolves to the success message.
 */
export const register = (userData: RegisterRequest): Promise<string> => {
  return api.post('/auth/create-user', userData);
};