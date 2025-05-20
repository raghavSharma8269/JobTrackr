import WelcomePage from "./pages/WelcomePage";
import MainPage from "./pages/MainPage";
import { Routes, Route } from "react-router-dom";
import PrivateRoute from "./PrivateRoute.tsx";

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
      </Routes>
    </div>
  );
}

export default App;
