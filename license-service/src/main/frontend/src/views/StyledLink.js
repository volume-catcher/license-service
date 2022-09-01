import React from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';


const StyledLink = styled(Link)`
    text-decoration: inherit;
    color: inherit;

    &:focus, &:hover, &:visited, &:link, &:active {
        text-decoration: inherit;
    }
`;

export default (props) => <StyledLink {...props} />;