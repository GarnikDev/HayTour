import { createContext, useContext, useEffect, useState } from "react";
import axios from "../api/axiosConfig";

const AuthContext = createContext();

export function AuthProvider({ children }, userData) {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [username, setUsername] = useState({userData});

  const checkAuth = async () => {
    try {
      const res = await axios.get("/me", {
        withCredentials: true
      });
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
    await axios.post("/logout", user, {
      withCredentials: true
    });
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, setUser, logout, loading }}>
      {children}
    </AuthContext.Provider>
  );
}

export const useAuth = () => useContext(AuthContext);
