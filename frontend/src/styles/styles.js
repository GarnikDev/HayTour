// src/styles.js

export const themes = {
  // Theme 1: "Modern Heritage" (Inspired by One Way Tour - Professional & Trustworthy)
  modernHeritage: {
    "--primary-color": "#003580", // Deep Navy
    "--secondary-color": "#ffb700", // Golden Yellow (Accent)
    "--bg-color": "#f0f2f5",
    "--card-bg": "#ffffff",
    "--text-main": "#1a1a1a",
    "--text-muted": "#6a6a6a",
    "--border-radius": "12px",
    "--button-padding": "12px 24px",
    "--font-family": "'Poppins', sans-serif"
  },

  // Theme 2: "Eco Adventure" (Fresh, Green, Outdoorsy)
  ecoAdventure: {
    "--primary-color": "#2d6a4f", // Forest Green
    "--secondary-color": "#d4a373", // Earthy Tan
    "--bg-color": "#f8f9fa",
    "--card-bg": "#ffffff",
    "--text-main": "#081c15",
    "--text-muted": "#40916c",
    "--border-radius": "4px",
    "--button-padding": "10px 20px",
    "--font-family": "'Inter', sans-serif"
  }

};

export const activeTheme = themes.modernHeritage;