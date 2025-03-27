const SearchBarComponent = () => {
  return (
    <nav className="navbar bg-body-tertiary">
      <div className="container-fluid">
        <div className="dropdown">
          <button
            className="btn btn-secondary dropdown-toggle"
            type="button"
            data-bs-toggle="dropdown"
            aria-expanded="false"
          >
            Dropdown button
          </button>
          <ul className="dropdown-menu">
            <li>
              <a className="dropdown-item" href="#">
                Sort By Company Name
              </a>
            </li>
            <li>
              <a className="dropdown-item" href="#">
                Sort By Job Title
              </a>
            </li>
            <li>
              <a className="dropdown-item" href="#">
                Sort By Date Added
              </a>
            </li>
            <li>
              <a className="dropdown-item" href="#">
                Show Favorites
              </a>
            </li>
          </ul>
        </div>
        <form className="d-flex" role="search">
          <input
            className="form-control me-2"
            type="search"
            placeholder="Search"
            aria-label="Search"
          />
          <button className="btn btn-outline-success" type="submit">
            Search
          </button>
        </form>
      </div>
    </nav>
  );
};

export default SearchBarComponent;
