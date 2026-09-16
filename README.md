# Xen-Budgetier

A beautiful, glassmorphic personal finance and budget tracking dashboard built entirely as a client-side application. It runs 100% offline in your browser using local storage—meaning no backend, no databases, and no installations are required.

## Features

- **Privacy-First & Offline**: All your financial data is saved locally on your device via `localStorage`. No data ever leaves your browser.
- **Glassmorphic Design System**: A meticulously crafted UI featuring a warm, earthy palette (olive greens, caramel browns, beige), soft layered shadows, and ambient mesh gradients.
- **Interactive Analytics**: Dynamic, responsive charts (via Chart.js) that adapt to the theme and automatically recalculate as you log expenses.
- **Category Management**: Edit, add, and remove categories and sub-categories to tailor the tracker to your specific spending habits.
- **Mobile Optimized**: A robust bottom-navigation layout, iOS safe-area integrations, and touch-friendly targets ensure the app looks and feels like a native mobile app.
- **Fluid View Transitions**: Seamless ripple animations when toggling between dark and light modes, powered by the View Transitions API.

## File Structure

The entire application architecture is intentionally minimal, consisting of exactly three core files:

```
xen-budgetier/
├── index.html     # Semantic HTML structure, SVG icons, and modal templates
├── style.css      # Vanilla CSS, glassmorphism design system, mobile media queries
├── script.js      # Vanilla JavaScript for state management, localStorage logic, and Chart.js integration
└── README.md      # You are here
```

## Tech Stack

- **HTML5**: Semantic layout and structuring.
- **CSS3**: Custom properties (variables), Flexbox/Grid, and modern media queries. Zero CSS frameworks (no Tailwind, Bootstrap, etc.).
- **Vanilla JavaScript (ES6+)**: Handles all application logic, DOM manipulation, and state persistence. No frameworks (no React, Vue, Node.js).
- **Chart.js (via CDN)**: For rendering the daily bar charts, budget donuts, and cumulative trend lines.

## How to Run

1. Clone or download this repository to your local machine.
2. Double-click the `index.html` file to open it in your default web browser.
3. Start tracking your budget!


## Live Demo: 
 https://arnabbiswas100.github.io/xen-budgetier/
