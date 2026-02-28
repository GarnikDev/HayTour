// src/styles.js
import { createTheme } from '@mui/material/styles';
export const themes = {

  modernHeritage: {
    "--primary-color": "#003580",
    "--secondary-color": "#ffb700",
    "--bg-color": "#f4f7f9",
    "--bg-light": "#fafafa", // Added to support your booking container
    "--card-bg": "#ffffff",
    "--text-main": "#1a1a1a",
    "--text-muted": "#6a6a6a",
    "--border-radius": "20px",
    "--border-radius-sm": "12px",
    "--border-radius-lg": "30px",
    "--button-padding": "12px 28px",
    "--font-family": "'Poppins', sans-serif",
    "--btn-shadow": "0 4px 14px 0 rgba(0, 53, 128, 0.39)"
  },
  ecoAdventure: {
    "--primary-color": "#2d6a4f",
    "--secondary-color": "#d4a373",
    "--bg-color": "#f8f9fa",
    "--bg-light": "#ffffff",
    "--card-bg": "#ffffff",
    "--text-main": "#081c15",
    "--text-muted": "#40916c",
    "--border-radius": "8px",
    "--border-radius-sm": "4px",
    "--border-radius-lg": "12px",
    "--button-padding": "10px 20px",
    "--font-family": "'Inter', sans-serif",
    "--btn-shadow": "0 4px 10px rgba(45, 106, 79, 0.2)"
  }

};

export const activeTheme = themes.modernHeritage;

export const createAppTheme = () => createTheme({
  palette: {
    primary: { main: activeTheme["--primary-color"] },
    secondary: { main: activeTheme["--secondary-color"] }, // Fixes the purple button issue
  },
  shape: {
    borderRadius: parseInt(activeTheme["--border-radius"]),
  },
  typography: {
    fontFamily: activeTheme["--font-family"],
  },
  components: {
    MuiButton: {
      styleOverrides: {
        root: {
          borderRadius: '50px',
          textTransform: 'none',
          padding: activeTheme["--button-padding"],
        },
      },
    },
  },
});