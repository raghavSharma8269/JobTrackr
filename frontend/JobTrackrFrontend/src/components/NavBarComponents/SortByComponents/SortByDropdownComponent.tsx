import React from "react";
import SortBy_Up_Down_ButtonGroupComponent from "./SortBy_Up_Down_ButtonGroupComponent.tsx";
const SortByDropdownComponent = () => {
  return (
    <div className="dropdown">
      <button
        className="btn dropdown-toggle purple-bg default-text-color"
        data-bs-toggle="dropdown"
        aria-expanded="false"
        style={{ marginRight: "10px" }}
      >
        Sort By...
      </button>
      <ul
        className="dropdown-menu custom-filter-dropdown"
        style={{ backgroundColor: "#292b38" }}
      >
        <li>
          <a className="dropdown-item default-text-color" href="#">
            Company Name
          </a>
        </li>
        <li>
          <a className="dropdown-item default-text-color" href="#">
            Job Title
          </a>
        </li>
        <li>
          <a className="dropdown-item default-text-color" href="#">
            Date Added
          </a>
        </li>
        <li>
          <a className="dropdown-item default-text-color" href="#">
            Favorites
          </a>
        </li>
      </ul>
      <SortBy_Up_Down_ButtonGroupComponent />
    </div>
  );
};
export default SortByDropdownComponent;
