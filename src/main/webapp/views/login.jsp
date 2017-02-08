<div class="container">
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Please Sign In</h3>
				</div>
				<div class="panel-body">
					<form ng-submit="formSubmit()" class="form">
						<fieldset>
							<div class="form-group">
								<input type="text" class="form-control" ng-model="email"
									placeholder="email" required="" autofocus />
							</div>
							<div class="form-group">
								<input type="password" class="form-control" ng-model="password"
									placeholder="password" required="" />
							</div>
							<div class="checkbox">
								<label> <input name="remember" type="checkbox"
									value="Remember Me">Remember Me
								</label>
							</div>

							<div class="form-group">
								<button type="submit" class="btn btn-lg btn-success btn-block">Login</button>
								<span class="text-danger">{{ error }}</span>
							</div>

						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>