import React from 'react'

const SetStatusDropdownComponent = () => {
    return (
        <div className="dropdown mb-4">
            <button className="btn btn-secondary dropdown-toggle text-start" type="button"
                    data-bs-toggle="dropdown"
                    aria-expanded="false">
                Set Status...
            </button>
            <ul className="dropdown-menu">
                <li><a className="dropdown-item" href="#">Applied</a></li>
                <li><a className="dropdown-item" href="#">Interview</a></li>
                <li><a className="dropdown-item" href="#">Accepted</a></li>
                <li><a className="dropdown-item" href="#">Rejected</a></li>
            </ul>
        </div>    )
}
export default SetStatusDropdownComponent
