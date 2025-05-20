import React from "react";

interface Props {
  description: string;
}

const DescriptionComponent: React.FC<Props> = ({ description }) => {
  return <div>{description}</div>;
};
export default DescriptionComponent;
