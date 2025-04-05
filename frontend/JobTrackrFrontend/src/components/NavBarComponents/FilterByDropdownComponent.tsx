import React from 'react'

const FilterByDropdownComponent = () => {
    return (
        <div className="dropdown" style={{marginLeft:"10px"}}>
            <button className="btn  dropdown-toggle text-start" type="button"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
            style={{backgroundColor:"#7400f0", color:"#c9c9c9"}}>
                Filter...
            </button>
            <ul className="dropdown-menu" style={{backgroundColor:"#292b38"}}>
                <li><a className="dropdown-item default-text-color" style={{backgroundColor:"blue", borderRadius:"10px"}} href="#">Applied</a></li>
                <li><a className="dropdown-item default-text-color" style={{backgroundColor:"green", borderRadius:"10px"}} href="#">Interview</a></li>
                <li><a className="dropdown-item default-text-color" style={{backgroundColor:"#7400f0", borderRadius:"10px"}} href="#">Accepted</a></li>
                <li><a className="dropdown-item default-text-color" style={{backgroundColor:"red", borderRadius:"10px"}} href="#">Rejected</a></li>
            </ul>


        </div>
    )
}
export default FilterByDropdownComponent
