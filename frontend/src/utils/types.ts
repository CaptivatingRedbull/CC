/**
 * Represents the kind of content (e.g., Movie, Series).
 */
export type Kind = 'MOVIE' | 'SERIES';

/**
 * Represents a single content item.
 */
export interface Content {
  id: number;
  kind: Kind;
  title: string;
  description: string;
  year: number;
  upVote: number;
  downVote: number;
  score: number;
}

/**
 * Represents a paginated response from the API.
 */
export interface Page<T> {
    content: T[];
    page: {
        totalPages: number;
        totalElements: number;
        size: number;
        number: number;
    };
}

/**
 * Represents the request body for user authentication.
 */
export interface AuthRequest {
  username?: string;
  password?: string;
}

/**
 * Represents the response from a successful authentication.
 */
export interface AuthResponse {
  token: string;
  username: string;
}

/**
 * Represents the request body for user registration.
 */
export interface RegisterRequest {
  username?: string;
  password?: string;
}
