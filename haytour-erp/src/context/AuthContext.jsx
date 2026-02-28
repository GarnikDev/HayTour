import { createContext, useContext, useEffect, useState } from "react";
import axios from "../api/axiosConfig";

const AuthContext = createContext();

export function AuthProvider({ children }) { // Removed unused userData
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);

    const checkAuth = async () => {
        try {
            const res = await axios.get("/me");
            setUser(res.data);
        } catch {
            setUser(null);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        checkAuth();
    }, []);

    const logout = async () => {
        try {
            await axios.post("/logout"); // No body needed
        } catch {} // Silent fail ok for logout
        setUser(null);
    };

    return (
        <AuthContext.Provider value={{ user, setUser, logout, loading, checkAuth }}>
            {children}
        </AuthContext.Provider>
    );
}

export const useAuth = () => useContext(AuthContext);