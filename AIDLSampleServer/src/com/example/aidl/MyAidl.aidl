/* The package where the aidl file is located */
package com.example.aidl;

/* The name of the remote service */
interface MyAidl {

	/* A simple Method which will return a message
	 * The message object implements the Parcelable interface 
	 */
	String getMessage();

}