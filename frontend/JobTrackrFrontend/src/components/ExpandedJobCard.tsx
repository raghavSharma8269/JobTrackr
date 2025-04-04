import DescriptionComponent from "./ExpandedJobCardComponents/DescriptionComponent";
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
                className="col-12 col-md"
                style={{
                  borderBottom: "1px solid #ccc", // Correct camelCase for borderBottom
                  paddingTop: "15px",
                }}
            >

              <h3 className="text-start">Job Title</h3>
              <h5 className="text-start">Company Name</h5>
              <p className="text-start">Location</p>
              <p className="text-start">Date Added</p>
            </div>
            <div
                className="col-12 col-md"
                style={{
                  borderBottom: "1px solid #ccc", // Correct camelCase for borderBottom
                }}
            >


              {/* This is the 2nd column with buttons*/}

              <div className="container text-center" style={{paddingTop: "15px", paddingBottom: "15px"}}>
                <div className="row">
                  <button type="button" className="col">Primary</button>

                  <button type="button" className="col">Primary</button>

                  <button type="button" className="col">Primary</button>

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
                        maxHeight: "calc(100vh - 469px)"

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
