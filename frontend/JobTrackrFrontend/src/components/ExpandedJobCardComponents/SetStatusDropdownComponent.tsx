import DeleteJobIcon from "./DeleteJobIcon.tsx";
import SetStatusButtonComponent from "./SetStatusButtonComponent.tsx";
import { useState } from "react";

const SetStatusDropdownComponent = () => {
  const [activeSection, setActiveSection] = useState<
    "applied" | "interview" | "accepted" | "rejected" | "none"
  >("none");

  return (
    <div className="dropdown mb-4" data-bs-display="static">
      {activeSection === "none" && <SetStatusButtonComponent />}
      {activeSection === "applied" && (
        <SetStatusButtonComponent
          className="btn blue-bg dropdown-toggle default-text-color"
          text="Applied"
        />
      )}

      {activeSection === "interview" && (
        <SetStatusButtonComponent
          className="btn green-bg dropdown-toggle default-text-color"
          text="Interview"
        />
      )}
      {activeSection === "accepted" && (
        <SetStatusButtonComponent
          className="btn purple-bg dropdown-toggle default-text-color"
          text="Accepted"
        />
      )}
      {activeSection === "rejected" && (
        <SetStatusButtonComponent
          className="btn red-bg dropdown-toggle default-text-color"
          text="Rejected"
        />
      )}
      <ul
        className="dropdown-menu force-drop-down"
        style={{ backgroundColor: "#292b38" }}
      >
        <li>
          <a
            className="dropdown-item"
            style={{
              backgroundColor: "blue",
              borderRadius: "10px",
              color: "white",
              height: "25px",
              fontSize: ".9rem",
            }}
            onClick={() => setActiveSection("applied")}
            href="#"
          >
            Applied
          </a>
        </li>
        <li>
          <a
            className="dropdown-item"
            style={{
              backgroundColor: "green",
              borderRadius: "10px",
              color: "white",
              height: "25px",
              fontSize: ".9rem",
            }}
            onClick={() => setActiveSection("interview")}
            href="#"
          >
            Interview
          </a>
        </li>
        <li>
          <a
            className="dropdown-item"
            style={{
              backgroundColor: "#7400f0",
              borderRadius: "10px",
              color: "white",
              height: "25px",
              fontSize: ".9rem",
            }}
            href="#"
            onClick={() => setActiveSection("accepted")}
          >
            Accepted
          </a>
        </li>
        <li>
          <a
            className="dropdown-item"
            style={{
              backgroundColor: "red",
              borderRadius: "10px",
              color: "white",
              height: "25px",
              fontSize: ".9rem",
            }}
            href="#"
            onClick={() => setActiveSection("rejected")}
          >
            Rejected
          </a>
        </li>
        <li>
          <a
            className="dropdown-item"
            style={{
              backgroundColor: "gray",
              borderRadius: "10px",
              color: "white",
              height: "25px",
              fontSize: ".9rem",
            }}
            href="#"
            onClick={() => setActiveSection("none")}
          >
            Reset...
          </a>
        </li>
      </ul>
      <DeleteJobIcon />
    </div>
  );
};
export default SetStatusDropdownComponent;
