module.exports = {
  env: {
    browser: true,
    es2021: true,
  },
  extends: ["plugin:prettier/recommended", "airbnb", "eslint-config-prettier"],
  parserOptions: {
    ecmaVersion: "latest",
    sourceType: "module",
  },
  plugins: ["react", "react-hooks", "prettier"],
  rules: {
    "react/jsx-filename-extension": ["warn", { extensions: [".js"] }],
    "react/function-component-definition": [
      2,
      {
        namedComponents: "arrow-function",
        unnamedComponents: "arrow-function",
      },
    ],
    "react/prop-types": ["off"],
    "no-unused-vars": ["off"],
    "no-console": ["off"],
    "import/prefer-default-export": ["off"],
    "react-hooks/exhaustive-deps": ["warn"],
    "react/jsx-props-no-spreading": ["warn"],
    "prettier/prettier": [
      "error",
      {
        endOfLine: "auto",
      },
    ],
    "import/no-extraneous-dependencies": ["error", { devDependencies: true }],
  },
  settings: {
    "import/resolver": {
      node: {
        extensions: [".js", ".jsx", ".ts", ".tsx"],
        moduleDirectory: ["node_modules", "src/"],
      },
    },
  },
};
