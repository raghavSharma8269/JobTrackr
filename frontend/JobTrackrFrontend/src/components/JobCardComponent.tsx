import FavoriteStarComponent from "./ExpandedJobCardComponents/FavoriteStarComponent";

interface JobCardProps {
  onClick?: () => void;
  isFavorite: boolean;
  onToggleFavorite: () => void;
}

const JobCardComponent: React.FC<JobCardProps> = ({
  onClick,
  isFavorite,
  onToggleFavorite,
}) => {
  return (
    <div className="container mt-1">
      <div className="row-100">
        <button
          type="button"
          onClick={onClick}
          className="btn btn-lg w-100 job-card"
          style={{
            backgroundColor: "#292b38",
            color: "#c9c9c9",
            height: "140px",
            marginBottom: "5px",
            position: "relative",
          }}
        >
          <div className="container align-items-start text-start">
            <h5>
              Job Title
              <span className="float-end">
                <FavoriteStarComponent
                  isFavorite={isFavorite}
                  onToggle={onToggleFavorite}
                />
              </span>
            </h5>
            <p style={{ fontSize: "15px" }}>Company Name</p>
            <p style={{ fontSize: "15px" }}>Location</p>
          </div>
        </button>
      </div>
    </div>
  );
};

export default JobCardComponent;
