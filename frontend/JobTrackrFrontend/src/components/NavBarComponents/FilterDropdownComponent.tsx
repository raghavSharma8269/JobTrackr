import React from 'react'

const FilterDropdownComponent = () => {
    return (
        <div className="dropdown">
            <button
                className="btn dropdown-toggle default-text-color"
                type="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
                style={{backgroundColor:"#7400f0"}}

            >
                Sort By...
            </button>
            <ul className="dropdown-menu custom-filter-dropdown" style={{backgroundColor:"#292b38"}}>
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
        </div>    )
}
export default FilterDropdownComponent
