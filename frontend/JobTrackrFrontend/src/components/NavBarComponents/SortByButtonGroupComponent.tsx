import React from 'react';
import SortDownIconComponent from './SortDownIconComponent';
import SortUpIconComponent from './SortUpIconComponent';

const SortByButtonGroupComponent = () => {
    return (
        <div className="btn-group" role="group" aria-label="Sort toggle button group">

            <input
                type="radio"
                className="btn-check"
                name="btnradio"
                id="btnradio1"
                autoComplete="off"
            />
            <label className="btn btn-outline-primary" htmlFor="btnradio1">
                <SortUpIconComponent />
            </label>

            <input
                type="radio"
                className="btn-check"
                name="btnradio"
                id="btnradio2"
                autoComplete="off"
                defaultChecked
            />
            <label className="btn btn-outline-primary" htmlFor="btnradio2">
                <SortDownIconComponent />
            </label>

        </div>
    );
};

export default SortByButtonGroupComponent;
