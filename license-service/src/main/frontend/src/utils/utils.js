export const isNotEmptyArray = (value) => {
  return Array.isArray(value) && value.length > 0;
};

export const isNotEmptyString = (value) => {
  return (
    (typeof value === "string" || value instanceof String) && value.length > 0
  );
};
