import api from './api';
import type { Content, Page, Kind } from '@/utils/types';

/**
 * Fetches a paginated list of contents with optional filters.
 * @param page - The page number to retrieve.
 * @param size - The number of items per page.
 * @param sortBy - The field to sort by.
 * @param sortDirection - The direction of sorting ('asc' or 'desc').
 * @param search - Optional search term.
 * @param kind - Optional kind to filter by.
 * @returns A promise that resolves to a Page of Content.
 */
export const getAllContents = (
  page: number,
  size: number,
  sortBy: string,
  sortDirection: string,
  search?: string,
  kind?: Kind
): Promise<Page<Content>> => {
  return api.get('/contents', {
    params: { page, size, sortBy, sortDirection, search, kind },
  });
};

/**
 * Fetches a single content item by its ID.
 * @param id - The ID of the content to retrieve.
 * @returns A promise that resolves to the Content.
 */
export const getContentById = (id: number): Promise<Content> => {
  return api.get(`/contents/${id}`);
};

/**
 * Creates a new content item.
 * @param content - The content data to create.
 * @returns A promise that resolves to the created Content.
 */
export const createContent = (content: Omit<Content, 'id'>): Promise<Content> => {
  return api.post('/contents', content);
};

/**
 * Updates an existing content item.
 * @param id - The ID of the content to update.
 * @param contentDetails - The new details for the content.
 * @returns A promise that resolves to the updated Content.
 */
export const updateContent = (id: number, contentDetails: Content): Promise<Content> => {
  return api.put(`/contents/${id}`, contentDetails);
};

/**
 * Deletes a content item by its ID.
 * @param id - The ID of the content to delete.
 * @returns A promise that resolves when the deletion is complete.
 */
export const deleteContent = (id: number): Promise<void> => {
  return api.delete(`/contents/${id}`);
};

/**
 * Adds an upvote to a content item.
 * @param id - The ID of the content to upvote.
 * @returns A promise that resolves when the upvote is successful.
 */
export const addUpVote = (id: number): Promise<void> => {
  return api.put(`/contents/${id}/upvote`);
};

/**
 * Adds a downvote to a content item.
 * @param id - The ID of the content to downvote.
 * @returns A promise that resolves when the downvote is successful.
 */
export const addDownVote = (id: number): Promise<void> => {
  return api.put(`/contents/${id}/downvote`);
};

/**
 * Removes an upvote from a content item.
 * @param id - The ID of the content.
 * @returns A promise that resolves when the action is successful.
 */
export const removeUpVote = (id: number): Promise<void> => {
  return api.delete(`/contents/${id}/upvote`);
};

/**
 * Removes a downvote from a content item.
 * @param id - The ID of the content.
 * @returns A promise that resolves when the action is successful.
 */
export const removeDownVote = (id: number): Promise<void> => {
  return api.delete(`/contents/${id}/downvote`);
};
