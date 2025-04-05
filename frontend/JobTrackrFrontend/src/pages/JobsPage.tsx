import ExpandedJobCard from "../components/ExpandedJobCard";
import JobListComponent from "../components/JobListComponent";
import SearchBarComponent from "../components/SearchBarComponent";


const JobsPage = () => {
  return (
      <div className="container-fluid min-vh-100 d-flex align-items-center">
        <div className="row w-100">
            <SearchBarComponent/>
          {/* Ensure this column takes enough space for scrolling */}
          <div className="col-md-4 d-flex justify-content-center align-items-start overflow-hidden">
            <JobListComponent />
          </div>
          {/* Adjust ExpandedJobCard positioning */}
          <div className="col-md-8 d-flex justify-content-center align-items-start">
            <ExpandedJobCard isVisible={true} />
          </div>
        </div>
      </div>
  );
};
export default JobsPage;
