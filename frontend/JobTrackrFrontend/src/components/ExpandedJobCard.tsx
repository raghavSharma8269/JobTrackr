interface ExpandedJobCardProps {
  isVisible: boolean;
}


const ExpandedJobCard: React.FC<ExpandedJobCardProps> = ({ isVisible }) => {
  if (!isVisible) return null;
  return (
    <div className="default-text-color" style={{ backgroundColor: "#292b38" }}>
      ExpandedJobCard
    </div>
  );
};

export default ExpandedJobCard;
