import WelcomePage from "./pages/WelcomePage";
import MainPage from "./pages/MainPage";
import { Routes, Route } from "react-router-dom";
import PrivateRoute from "./PrivateRoute.tsx";
import ContactPage from "./pages/ContactPage.tsx";

function App() {
  return (
    <div style={{ backgroundColor: "#1c1d26", minHeight: "100vh" }}>
      <Routes>
        <Route
          path="/dashboard"
          element={
            <PrivateRoute>
              <MainPage />
            </PrivateRoute>
          }
        />
        <Route path="/welcome" element={<WelcomePage />} />
        <Route path="/contact" element={<ContactPage />} />
      </Routes>
    </div>
  );
}

export default App;
