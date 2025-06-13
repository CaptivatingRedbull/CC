import './App.css';
import { Route, Routes } from 'react-router-dom';
import { ContentPage } from './pages/contentPage';
import { AdminPage } from './pages/adminPage';

export function App() {
    return (
        <Routes>
            {/* === PUBLIC ROUTES === */}
            <Route path="/" element={<AdminPage />} />

            {/* === CATCH-ALL ROUTE === */}
            <Route path="*" element={<ContentPage />} />
        </Routes>
    );
}
