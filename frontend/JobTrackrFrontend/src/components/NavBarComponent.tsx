import SortByDropdownComponent from "./NavBarComponents/SortByDropdownComponent.tsx";
import AddJobIcon from "./NavBarComponents/AddJobIcon.tsx";
const NavBarComponent = () => {
  return (
    <nav
      className="navbar"
      style={{
        marginBottom: "-40px",
        borderRadius: "10px",
        backgroundColor: "#292b38",
        paddingTop: "15px",
        paddingBottom: "15px",
      }}
    >
      <div className="container-fluid" style={{ backgroundColor: "#292b38" }}>
        <SortByDropdownComponent />
        <AddJobIcon />
        <form className="d-flex" role="search">
          <input
            className="form-control me-2"
            type="search"
            placeholder="Search"
            aria-label="Search"
            style={{ backgroundColor: "#1c1d26", color: "#9e9ca1" }}
          />
          <button className="btn purple-bg default-text-color">Search</button>
        </form>
      </div>
    </nav>
  );
};

export default NavBarComponent;
