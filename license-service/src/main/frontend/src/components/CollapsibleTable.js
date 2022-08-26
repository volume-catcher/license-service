import React, { useState, Fragment } from 'react';
import PropTypes from 'prop-types';
import Box from '@mui/material/Box';
import Collapse from '@mui/material/Collapse';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import EditIcon from '@mui/icons-material/Edit';
import CheckIcon from '@mui/icons-material/Check';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';
import TextField from '@mui/material/TextField';
import Autocomplete from '@mui/material/Autocomplete';
import { styled, createTheme } from '@mui/material/styles';
import dayjs from 'dayjs';
import { DateTimePicker } from '@mui/x-date-pickers/DateTimePicker';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';

function createData(licenseKey, productName, numOfAuthAvailable1, isActivated1, expireAt1) {
  return {
    licenseKey,
    productName,
    product: [
      {
        numOfAuthAvailable: numOfAuthAvailable1,
        isActivated: isActivated1? '활성화': '비활성화',
        expireAt: expireAt1,
      }
    ],
  };
}

function editRow(props) {

  return (
    <>
    
    </>
  )
}

function Row(props) {
  const { row } = props;
  const [open, setOpen] = useState(false);
  const [edit, setEdit] = useState(false);
  
  const theme = createTheme({
    palette: {
      backgroundGray: '#F6F6F6',
      hoverGray: '#E9E9E9',
      titleGray: '#6E6E6E',
    }
  });
  
  const StyledTableRow = styled(TableRow)(() => ({
    backgroundColor: theme.palette.backgroundGray,
    '&:hover': {
      background: theme.palette.hoverGray,
    },
  }));

  return (
    <Fragment>
      <StyledTableRow sx={{ '& > *': { borderBottom: 'unset' } }}>
        <TableCell>
          <IconButton
            aria-label="expand row"
            size="small"
            onClick={() => setOpen(!open)}
          >
            {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
          </IconButton>
        </TableCell>
        <TableCell component="th" scope="row">{row.productName}</TableCell>
        <TableCell>
          <IconButton
              aria-label="expand row"
              size="small"
              onClick={() => setEdit(!edit)}
            >
              {edit ? <CheckIcon /> : <EditIcon />}
            </IconButton>
        </TableCell>
      </StyledTableRow>
      <TableRow>
        <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
          <Collapse in={open} timeout="auto" unmountOnExit>
            <Box sx={{ margin: 1 }}>
            {
              row.product.map((productRow) => (
                <>
                  <Typography sx={{ color: theme.palette.titleGray, fontSize: 14, marginTop: 2 }}>
                    제품별 인증 가능 횟수
                  </Typography>
                  <Typography >
                    {productRow.numOfAuthAvailable}
                  </Typography>
                  <Typography sx={{ color: theme.palette.titleGray, fontSize: 14, marginTop: 2 }}>
                    활성 여부
                  </Typography>
                  <Typography >
                    {productRow.isActivated}
                  </Typography>
                  <Typography sx={{ color: theme.palette.titleGray, fontSize: 14, marginTop: 2 }}>
                    만료 일시
                  </Typography>
                  <Typography >
                    {productRow.expireAt}
                  </Typography>
                </>
              ))
            }
            </Box>
          </Collapse>
        </TableCell>
      </TableRow>
    </Fragment>
  );
}

Row.propTypes = {
  row: PropTypes.shape({
    licenseKey: PropTypes.string.isRequired,
    product: PropTypes.arrayOf(
      PropTypes.shape({
        productName: PropTypes.string.isRequired,
        numOfAuthAvailable: PropTypes.number.isRequired,
        isActivated: PropTypes.bool.isRequired,
        expireAt: PropTypes.string.isRequired,
      }),
    ).isRequired,
  }).isRequired,
};

const rows = [
  createData('JF3I-3KF9-FNB3-JEJB', 'PowerPoint', 5, true, Date.now()),
  createData('JF3I-3KF9-FNB3-JEJC', 'Word', 5, true, Date.now()),
  createData('JF3I-3KF9-FNB3-JEJD', 'Excel', 5, true, Date.now()),
  createData('JF3I-3KF9-FNB3-JEJE', 'OneNote', 5, true, Date.now()),
  createData('JF3I-3KF9-FNB3-JEJF', 'OneDrive', 5, true, Date.now()),
  createData('JF3I-3KF9-FNB3-JEJG', 'Access', 5, false, Date.now()),
  createData('JF3I-3KF9-FNB3-JEJH', 'Publisher', 5, true, Date.now()),
  createData('JF3I-3KF9-FNB3-JEJI', 'Teams', 5, true, Date.now()),
  createData('JF3I-3KF9-FNB3-JEJJ', 'Outlook', 5, false, Date.now()),
  createData('JF3I-3KF9-FNB3-JEJK', 'PowerPoint2', 5, false, Date.now()),
  createData('JF3I-3KF9-FNB3-JEJL', 'Word2', 5, false, Date.now()),
]

const products = [
  'PowerPoint',
  'Word',
  'Excel',
  'OneNote',
  'OneDrive',
  'Access',
  'Publisher',
  'Teams',
  'Outlook',
  'PowerPoint2',
  'Word2',
  'PowerPoint3',
  'Word3',
  'Excel3',
  'OneNote3',
  'OneDrive3',
  'Access3',
  'Publisher3',
  'Teams3',
  'Outlook3',
  'PowerPoint4',
  'Word4',
]

export default function CollapsibleTable(props) {
  const [add, setAdd] = useState(false);
  const [numOfAuthAvailable, setNumOfAuthAvailable] = useState(5);
  const [isActivated, setIsActivated] = useState(false);
  const weekInMilliseconds = 7*24*60*60*1000;
  const [expireAt, setExpireAt] = useState(dayjs(Date.now() + weekInMilliseconds));

  return (
    <Paper sx={{ width: 800}}> {/*TODO: 삭제하기 */}
    <TableContainer component={Paper}>
      <Table aria-label="collapsible table">
        <TableHead>
          <TableRow>
            <TableCell>
              <Typography variant="h6" gutterBottom component="div">
                제품
              </Typography>
            </TableCell>
            <TableCell>
              <Button  
                variant="contained"
                size="small"
                onClick={() => setAdd(!add)}
              >
              {add ? "저장" : "추가" }
            </Button>
            </TableCell>
          </TableRow>
          <TableRow>
            <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
              <Collapse in={add} timeout="auto" unmountOnExit>
              <Box sx={{ margin: 1 }}>
              <Autocomplete
                disablePortal
                id="combo-box-demo"
                options={
                  rows
                  .flatMap(row => row.productName)
                  .concat(products)
                  .filter(item => 
                    !rows.flatMap(row => row.productName).includes(item) 
                    || !products.includes(item))
                }
                sx={{ width: 300 }}
                renderInput={(params) => <TextField {...params} label="제품" />}
              />
              <Typography sx={{ color: '#6E6E6E', fontSize: 14, marginTop: 2 }}>
                제품별 인증 가능 횟수
              </Typography>
              <TextField
                id="filled-number"
                type="number"
                InputLabelProps={{
                  shrink: true,
                }}
                variant="outlined"
                value={numOfAuthAvailable}
                InputProps={{
                  inputProps: { min: 0 }
                }}
                onChange={(e) => setNumOfAuthAvailable(e.target.value)}
              />
              <Typography sx={{ color: '#6E6E6E', fontSize: 14, marginTop: 2 }}>
                활성 여부
              </Typography>
              <Button variant="contained" color="info" onClick={() => setIsActivated(!isActivated)}>
                {isActivated? "활성화": "비활성화" }
              </Button>
              <Typography sx={{ color: '#6E6E6E', fontSize: 14, marginTop: 2 }}>
                만료 일시
              </Typography>
              <LocalizationProvider dateAdapter={AdapterDayjs}>
                <DateTimePicker
                  // label="만료일시"
                  value={expireAt}
                  onChange={(e) => setExpireAt(e)}
                  disablePast={true} 
                  renderInput={(params) => <TextField {...params} />}
                />
              </LocalizationProvider>
              </Box>
              </Collapse>
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map((row) => (
            <Row key={row.licenseKey} row={row} />
          ))}
        </TableBody>
      </Table>
    </TableContainer>
    </Paper>
  );
}
