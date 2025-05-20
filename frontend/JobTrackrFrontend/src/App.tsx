import WelcomePage from "./pages/WelcomePage";
import MainPage from "./pages/MainPage";
import { Routes, Route } from "react-router-dom";

function App() {
  return (
    <div style={{ backgroundColor: "#1c1d26", minHeight: "100vh" }}>
      <Routes>
        <Route path="/dashboard" element={<MainPage />} />
        <Route path="/welcome" element={<WelcomePage />} />
      </Routes>
    </div>
  );
}

export default App;
