import SortByDropdownComponent from "./NavBarComponents/SortByComponents/SortByDropdownComponent.tsx";
import AddJobIcon from "./NavBarComponents/AddJobIcon.tsx";

interface NavBarProps {
  search: string;
  setSearch: (search: string) => void;
  onSearch: () => void;
}

const NavBarComponent: React.FC<NavBarProps> = ({
  search,
  setSearch,
  onSearch,
}) => {
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
        <form
          className="d-flex"
          role="search"
          onSubmit={(e) => {
            e.preventDefault(); // Prevent form reload
            onSearch(); // trigger the search function
          }}
        >
          <input
            className="form-control me-2"
            type="search"
            placeholder="Search"
            aria-label="Search"
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            style={{ backgroundColor: "#1c1d26", color: "#9e9ca1" }}
          />
          <button className="btn purple-bg default-text-color">
            <i className="bi bi-search"></i>
          </button>
        </form>
      </div>
    </nav>
  );
};

export default NavBarComponent;
