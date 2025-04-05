import React from 'react'

const FilterByDropdownComponent = () => {
    return (
        <div className="dropdown">
            <button
                className="btn dropdown-toggle default-text-color"
                type="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
                style={{backgroundColor:"#7400f0", marginLeft:"10px"}}


            >
                Filter By...
            </button>
            <ul className="dropdown-menu custom-filter-dropdown" style={{backgroundColor:"#292b38"}}>
                <li>
                    <a className="dropdown-item default-text-color" href="#">
                        Applied
                    </a>
                </li>
                <li>
                    <a className="dropdown-item default-text-color" href="#">
                        Interview
                    </a>
                </li>
                <li>
                    <a className="dropdown-item default-text-color" href="#">
                        Accepted
                    </a>
                </li>
                <li>
                    <a className="dropdown-item default-text-color" href="#">
                        Rejected
                    </a>
                </li>
            </ul>
        </div>
    )
}
export default FilterByDropdownComponent
