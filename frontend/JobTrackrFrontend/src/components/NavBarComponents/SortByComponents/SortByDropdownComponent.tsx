import React, { useState } from "react";
import SortBy_Up_Down_ButtonGroupComponent from "./SortBy_Up_Down_ButtonGroupComponent.tsx";
import SortByDropdownButtonComponent from "./SortByDropdownButtonComponent.tsx";
const SortByDropdownComponent = () => {
  const [activeSection, setActiveSection] = useState<
    "companyName" | "jobTitle" | "dateAdded" | "favorites" | "none"
  >("none");

  return (
    <div className="dropdown">
      {activeSection === "none" && <SortByDropdownButtonComponent />}
      {activeSection === "companyName" && (
        <SortByDropdownButtonComponent text={"Company Name"} />
      )}
      {activeSection === "jobTitle" && (
        <SortByDropdownButtonComponent text={"Job Title"} />
      )}
      {activeSection === "dateAdded" && (
        <SortByDropdownButtonComponent text={"Date"} />
      )}
      {activeSection === "favorites" && (
        <SortByDropdownButtonComponent text={"Favorites"} />
      )}
      <ul
        className="dropdown-menu custom-filter-dropdown"
        style={{ backgroundColor: "#292b38" }}
      >
        <li>
          <a
            className="dropdown-item light-bg default-text-color"
            href="#"
            onClick={() => setActiveSection("jobTitle")}
          >
            Job Title
          </a>
        </li>
        <li>
          <a
            className="dropdown-item light-bg default-text-color"
            href="#"
            onClick={() => setActiveSection("companyName")}
          >
            Company Name
          </a>
        </li>
        <li>
          <a
            className="dropdown-item light-bg default-text-color"
            href="#"
            onClick={() => setActiveSection("dateAdded")}
          >
            Date
          </a>
        </li>
        <li>
          <a
            className="dropdown-item light-bg default-text-color"
            href="#"
            onClick={() => setActiveSection("favorites")}
          >
            Favorites
          </a>
        </li>
      </ul>
      <SortBy_Up_Down_ButtonGroupComponent />
    </div>
  );
};
export default SortByDropdownComponent;
