// src/i18n.js
import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';

const resources = {
  en: {
    translation: {
      login: "Login",
      email_label: "Email ID",
      email_helper: "Please enter a valid email address",
      password_label: "Password",
      submit: "Login",
      signup_prompt: "Don't have an account?",
      signup_link: "Sign Up",
    },
  },
  fr: {
    translation: {
      login: "Connexion",
      email_label: "Adresse e-mail",
      email_helper: "Veuillez entrer une adresse e-mail valide",
      password_label: "Mot de passe",
      submit: "Connexion",
      signup_prompt: "Vous n'avez pas de compte ?",
      signup_link: "S'inscrire",
    },
  },
};

i18n.use(initReactI18next).init({
  resources,
  lng: "en", // Default language
  fallbackLng: "en",
  interpolation: {
    escapeValue: false, // React already escapes values
  },
});

export default i18n;
