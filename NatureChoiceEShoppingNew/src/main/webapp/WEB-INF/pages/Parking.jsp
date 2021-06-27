<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Parking Lot</title>
 <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.min.css">
  <link rel="stylesheet" href="css/app.css">
  <link href="https://fonts.googleapis.com/css?family=Nunito+Sans:700,900&display=swap" rel="stylesheet">
</head>
<body>
			<div class="modal-header">
              <h4 class="modal-title" id="addnewModalTitle">Add new vehicle</h4>
            </div>
            <!-- User Create Modal Start -->
            <div class="modal-body">
              <!-- <form id="saveMemberInfo"> -->
              <form method="POST" action="${pageContext.request.contextPath}/ParkingLot">
                <div class="form-group">
                  <label for="reg_no">Registration no
                    <span class="required-field">*</span>
                  </label>
                  <input type="text" class="form-control" placeholder="Enter registration no:" id="reg_no" name="reg_no" required />
                </div>
                <div class="form-group">
                  <label for="owner_name">Owner name
                    <span class="required-field">*</span>
                  </label>
                  <input type="text" class="form-control" placeholder="Enter owner name:" id="owner_name" name="owner_name" required />
                </div>
                <div class="form-group">
                  <label for="slot">Parking slot
                    <span class="required-field">*</span>
                  </label>
                  <input type="number" class="form-control" placeholder="Enter Parking lot number" id="slot"  name="slot" required min="1" max="10" maxlength="2">
                </div>
                <div class="modal-footer-extended">
                  <button type="submit" class="btn btn-primary">Create</button>
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                </div>
              </form>
            </div>
            
  <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  <script src="js/app.js"></script>
</body>
</html>