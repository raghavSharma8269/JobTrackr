import JobCardComponent from "./JobCardComponent";

interface JobListComponentProps {
    onJobClick: (index: number) => void;
    favoriteJobs: boolean[];
    toggleFavorite: (index: number) => void;
}

const JobListComponent: React.FC<JobListComponentProps> = ({
                                                               onJobClick,
                                                               favoriteJobs,
                                                               toggleFavorite,
                                                           }) => {
    return (
        <div
            style={{
                height: "75vh",
                overflowY: "auto",
                overflowX: "hidden",
                marginTop: "100px",
            }}
            className="w-100 custom-scrollbar"
        >
            <div style={{ minHeight: "100%" }}>
                {[...Array(10)].map((_, index) => (
                    <div key={index} style={{ marginBottom: "10px" }}>
                        <JobCardComponent
                            onClick={() => onJobClick(index)}
                            isFavorite={favoriteJobs[index]}
                            onToggleFavorite={() => toggleFavorite(index)}
                        />
                    </div>
                ))}
            </div>
        </div>
    );
};

export default JobListComponent;
