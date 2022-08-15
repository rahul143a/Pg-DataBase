package com;

 class PersonNotFoundException extends Exception  {
 
		

		private String message;

		public  PersonNotFoundException(String message) {
			this.message = message;
		}
		@Override
		public String getMessage() {
			return message;
		}
	 
}
