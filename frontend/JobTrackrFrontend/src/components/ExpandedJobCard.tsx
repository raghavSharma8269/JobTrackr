import { useState } from "react";
import DescriptionComponent from "./ExpandedJobCardComponents/DescriptionComponent";
import FavoriteStarComponent from "./ExpandedJobCardComponents/FavoriteStarComponent";
import OpenJobLinkButtonComponent from "./ExpandedJobCardComponents/OpenJobLinkButtonComponent";
import SetStatusDropdownComponent from "./ExpandedJobCardComponents/SetStatusDropdownComponent.tsx";
import CvComponent from "./ExpandedJobCardComponents/CvComponent.tsx";
import ResumeComponent from "./ExpandedJobCardComponents/ResumeComponent.tsx";

interface ExpandedJobCardProps {
  isVisible: boolean;
  jobIndex: number | null;
  isFavorite: boolean;
  onToggleFavorite: () => void;
  status: "applied" | "interview" | "accepted" | "rejected" | "none";
  onUpdateStatus: (
    status: "applied" | "interview" | "accepted" | "rejected" | "none",
  ) => void;
}

const ExpandedJobCard: React.FC<ExpandedJobCardProps> = ({
  isVisible,
  jobIndex,
  isFavorite,
  onToggleFavorite,
  status,
  onUpdateStatus,
}) => {
  const [activeSection, setActiveSection] = useState<
    "description" | "cv" | "resume"
  >("description");

  if (!isVisible || jobIndex === null) return null;

  const jobData = {
    title: `Job Title ${jobIndex + 1}`,
    company: `Company ${jobIndex + 1}`,
    location: `Location ${jobIndex + 1}`,
    dateAdded: "4/4/2025",
  };

  return (
    <div
      className="container-fluid default-text-color"
      style={{
        backgroundColor: "#292b38",
        borderRadius: "10px",
        overflow: "hidden",
        marginTop: "100px",
      }}
    >
      <div className="container text-center">
        <div className="row d-flex flex-column">
          <div
            className="col-12 col-md text-start"
            style={{ borderBottom: "1px solid #9e9ca1" }}
          >
            <h3
              style={{ paddingTop: "20px" }}
              className="d-flex align-items-center justify-content-between"
            >
              {jobData.title}
              <div
                className="d-flex align-items-center"
                style={{ gap: "10px" }}
              >
                <FavoriteStarComponent
                  isFavorite={isFavorite}
                  onToggle={onToggleFavorite}
                />
                <div style={{ lineHeight: 0 }}>
                  <OpenJobLinkButtonComponent />
                </div>
              </div>
            </h3>
            <h5>{jobData.company}</h5>
            <p>{jobData.location}</p>
            <p>Date Added: {jobData.dateAdded}</p>

            <SetStatusDropdownComponent
              status={status}
              onUpdateStatus={onUpdateStatus}
            />
          </div>

          {/* Section tabs */}
          <div
            className="col-12 col-md"
            style={{ borderBottom: "1px solid #9e9ca1" }}
          >
            <div
              className="container text-center"
              style={{ paddingTop: "15px", paddingBottom: "15px" }}
            >
              <div className="row">
                {["description", "cv", "resume"].map((section) => (
                  <button
                    key={section}
                    className="col btn section-change-button"
                    style={{
                      backgroundColor:
                        activeSection === section ? "#1c1d26" : "#292b38",
                      color: "#9e9ca1",
                    }}
                    onClick={() =>
                      setActiveSection(section as typeof activeSection)
                    }
                  >
                    {section.charAt(0).toUpperCase() + section.slice(1)}
                  </button>
                ))}
              </div>
            </div>
          </div>

          {/* Section content */}
          <div
            className="col-12 col-md"
            style={{ paddingTop: "15px", paddingBottom: "15px" }}
          >
            <div
              className="custom-scrollbar"
              style={{
                overflowY: "auto",
                overflowX: "hidden",
                maxHeight: "calc(100vh - 579px)",
              }}
            >
              {activeSection === "description" && <DescriptionComponent />}
              {activeSection === "cv" && <CvComponent />}
              {activeSection === "resume" && <ResumeComponent />}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ExpandedJobCard;
