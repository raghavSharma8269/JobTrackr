import DescriptionComponent from "./ExpandedJobCardComponents/DescriptionComponent";
import FavoriteStarComponent from "./ExpandedJobCardComponents/FavoriteStarComponent";
import OpenJobLinkButtonComponent from "./ExpandedJobCardComponents/OpenJobLinkButtonComponent";
import SetStatusDropdownComponent from "./ExpandedJobCardComponents/SetStatusDropdownComponent";
interface ExpandedJobCardProps {
  isVisible: boolean;
}

const ExpandedJobCard: React.FC<ExpandedJobCardProps> = ({ isVisible }) => {
  if (!isVisible) return null;

  return (
      <div className="container-fluid default-text-color" style={{ backgroundColor: "#292b38", borderRadius:"10px", overflow:"hidden", marginTop:"100px"}}>
        <div className="container text-center">
          <div className="row d-flex flex-column">

            {/* This is the 1st column with job info*/}

              <div
                  className="col-12 col-md text-start"
                  style={{
                      borderBottom: "1px solid #9e9ca1",
                  }}
              >

                  <h3 style={{ paddingTop: "20px" }} className="d-flex align-items-center justify-content-between">
                      Job Title
                      <div className="d-flex align-items-center" style={{ gap: "10px" }}>
                          <FavoriteStarComponent />
                          <div style={{ lineHeight: 0 }}>
                              <OpenJobLinkButtonComponent />
                          </div>
                      </div>
                  </h3>


                  <h5>Company Name</h5>

                  <p>Location</p>

                  <p>Date Added: 4/4/2025</p>

                  <SetStatusDropdownComponent/>
              </div>
              <div
                  className="col-12 col-md"
                  style={{
                      borderBottom: "1px solid #9e9ca1",
                  }}
              >


                  {/* This is the 2nd column with buttons*/}

                  <div className="container text-center" style={{paddingTop: "15px", paddingBottom: "15px"}}>
                      <div className="row">
                          <button type="button" className="col btn" style={{backgroundColor:"#292b38", color:"#9e9ca1"}}>Description</button>

                    <button type="button" className="col btn" style={{backgroundColor:"#292b38", color:"#9e9ca1"}}>CV</button>

                    <button type="button" className="col btn" style={{backgroundColor:"#292b38", color:"#9e9ca1"}}>Resume</button>

                </div>
              </div>
            </div>

            {/* This is the 3rd column with description/cv/resume*/}

            <div
                className="col-12 col-md"
                style={{
                  paddingTop: "15px",
                  paddingBottom: "15px",
                }}
            >
                <div
                    className="custom-scrollbar"
                    style={{
                        overflowY: "auto",
                        overflowX: "hidden",
                        maxHeight: "calc(100vh - 579px)"

                    }}
                >
                    <DescriptionComponent/>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ExpandedJobCard;
