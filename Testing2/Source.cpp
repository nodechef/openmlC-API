#include <jni.h>
#include <iostream>
#include <string>

using namespace std;
int main()
{
	
	JavaVM *jvm;				// Pointer to the JVM (Java Virtual Machine)
	JNIEnv *env;				// Pointer to native interface

								//==================== prepare loading of Java VM ============================

	JavaVMInitArgs vm_args;                        // Initialization arguments
	JavaVMOption* options = new JavaVMOption[1];   // JVM invocation options
	options[0].optionString = "-Djava.class.path=./classes/lib/*.jar";
	options[0].optionString = "-Djava.class.path=./classes/openml.jar";   // where to find java .class
	vm_args.version = JNI_VERSION_1_6;             // minimum Java version
	vm_args.nOptions = 1;                          // number of options
	vm_args.options = options;
	vm_args.ignoreUnrecognized = false;     // invalid options make the JVM init fail

											//================= load and initialize Java VM and JNI interface ===============

	jint rc = JNI_CreateJavaVM(&jvm, (void**)&env, &vm_args);  // YES !!
	delete options;    // we then no longer need the initialisation options. 

					   //========================= analyse errors if any  ==============================
					   // if process interuped before error is returned, it's because jvm.dll can't be 
					   // found, i.e.  its directory is not in the PATH. 

	if (rc != JNI_OK) {
		if (rc == JNI_EVERSION)
			cerr << "FATAL ERROR: JVM is oudated and doesn't meet requirements" << endl;
		else if (rc == JNI_ENOMEM)
			cerr << "FATAL ERROR: not enough memory for JVM" << endl;
		else if (rc == JNI_EINVAL)
			cerr << "FATAL ERROR: invalid ragument for launching JVM" << endl;
		else if (rc == JNI_EEXIST)
			cerr << "FATAL ERROR: the process can only launch one JVM an not more" << endl;
		else
			cerr << "FATAL ERROR:  could not create the JVM instance (error code " << rc << ")" << endl;
		cin.get();
		exit(EXIT_FAILURE);
	}

	cout << "JVM load succeeded. \nVersion ";
	jint ver = env->GetVersion();
	cout << ((ver >> 16) & 0x0f) << "." << (ver & 0x0f) << endl;

	// First call to JAVA ==================================================================
	jclass cls2 = env->FindClass("com/openml/openml/mvn/main");  // try to find the class 

	if (cls2 == nullptr) {
		cerr << "ERROR: class not found !";
	}
	else {                                  // if class found, continue
		cout << "Class Main found" << endl;
		string flowCheckName ;
		string flowCheckVersion;
		bool mainCheck=true;
		string dataSetName;
		string dataSetDescription;
		string filename;
		string dataUrl;
		while (mainCheck) {

			cout << "<<<<<<<<========Welcome to OpenML C++ Api ===========>>>>>>>>" << endl;
			cout << "\n Select an option below : " << endl;
			cout << "\n 1. Press 1 to view all datasets : " << endl;
			cout << "\n 2. Press 2 to download a dataset : " << endl;
			cout << "\n 3. Press 3 to Upload a dataset : " << endl;
			cout << "\n 4. Press 4 to retrieve the description of the flow/implementation : " << endl;
			cout << "\n 5. Press 5 to retrieve the description of the Task : " << endl;
			cout << "\n 6. Press 6 to retrieve the description of the Run : " << endl;
			cout << "\n 7. Press any other key to Exit" << endl;
		
			int selection;
			cin >> selection;


			if (selection == 1) {
				cout << "Please enter your name : " << endl;
				cin >> dataSetName;
				jstring jStringParam = env->NewStringUTF(dataSetName.c_str());
				jmethodID mid2 = env->GetStaticMethodID(cls2, "dataSetList", "(Ljava/lang/String;)V");
				env->CallStaticVoidMethod(cls2, mid2, jStringParam);
				env->DeleteLocalRef(jStringParam);
			}

			else if (selection == 2) {
				int id;
				bool check = true;
				while (check) {
					cout << "Please enter the ID of DataSet : " << endl;
					cin >> id;
					jmethodID mid2 = env->GetStaticMethodID(cls2, "download", "()V");
					env->CallStaticVoidMethod(cls2, mid2, (jint)id);
					int downSelect;
					cout << "\n Select an option below :  " << endl;
					cout << "\n 1. Press 1 to retrieve the description of the features of dataset :  " << id << endl;
					cout << "\n 2. Press 2 to download the dataset : " << id << endl;
					cout << "\n 3. Press anyother key to continue :" << endl;
					cin >> downSelect;

					if (downSelect == 1) {
						// Check out and try this function in Java.And print result
						jmethodID mid2 = env->GetStaticMethodID(cls2, "datasetFeatures", "()V");
						env->CallStaticVoidMethod(cls2, mid2, (jint)id);
					}

					else if (downSelect == 2) {
						jmethodID mid2 = env->GetStaticMethodID(cls2, "datasetDownload", "()V");
						env->CallStaticVoidMethod(cls2, mid2, (jint)id);
					}
					char check2;
					cout << "\n Do you want to checkout anyother dataSet ? : Y or N : :" << endl;
					cin >> check2;
					if (check2 != 'Y') {
						check = false;
					}
				}
			}


			else if (selection == 3) {

				bool check = true;
				int uploadCheck;
				while (check) {
					cout << "Name of the DataSet :  " << endl;
					getline(cin, dataSetName); // Doesn't accept strings with spaces.

					cout << "Description of the DataSet :  " << endl;
					getline(cin, dataSetDescription); // Doesn't accept strings with spaces.

					cout << "Press 1 to upload file or Press 2 to post the URL of the file hosted somewhere else(Recommended if file size is > 100MB ) : " << endl;
					cin >> uploadCheck; 

					if (uploadCheck == 1) {
						cout << "Filename : " << endl;
						getline(cin, filename); // Doesn't accept strings with spaces.
						// Check out and try this function in Java.And print result
						jstring jStringParam1 = env->NewStringUTF(dataSetName.c_str());
						jstring jStringParam2 = env->NewStringUTF(dataSetDescription.c_str());
						jstring jStringParam3 = env->NewStringUTF(filename.c_str());
						jmethodID mid2 = env->GetStaticMethodID(cls2, "upload", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
						env->CallStaticVoidMethod(cls2, mid2, jStringParam1, jStringParam2, jStringParam3);
						env->DeleteLocalRef(jStringParam1);
						env->DeleteLocalRef(jStringParam2);
						env->DeleteLocalRef(jStringParam3);
					}

					else if (uploadCheck == 2) {
						cout << "Paste the URL of the dataSet : " << endl;
						cin >> dataUrl;
						jstring jStringParam1 = env->NewStringUTF(dataSetName.c_str());
						jstring jStringParam2 = env->NewStringUTF(dataSetDescription.c_str());
						jstring jStringParam3 = env->NewStringUTF(dataUrl.c_str());
						jmethodID mid2 = env->GetStaticMethodID(cls2, "uploadURL", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
						env->CallStaticVoidMethod(cls2, mid2, jStringParam1, jStringParam2, jStringParam3);
						env->DeleteLocalRef(jStringParam1);
						env->DeleteLocalRef(jStringParam2);
						env->DeleteLocalRef(jStringParam3);
					}
					char check2;
					cout << "\n Do you want to checkout anyother task ? : Y or N : :" << endl;
					cin >> check2;
					if (check2 != 'Y') {
						check = false;
					}
				}

			}

			else if (selection == 4) {
				int flowSelect;
				bool check = true;
				while (check) {
					cout << "Press 1 to Retrieve the description of flow : " << endl;
					cout << "Press 2 to Retrieve array of id's of all flows/implementations owned by you. :  " << endl;
					cout << "Press 3 to Check whether an implementation with the given name and version is already registered on OpenML : " << endl;
					cout << "Press 4 to Remove the flow with the given id (if you are its owner) : " << endl;
					cout << "Press 5 to Upload implementation files (binary and/or source) to OpenML. : " << endl;

					cin >> flowSelect;

					if (flowSelect == 1) {
						int flowID;
						cout << "Enter the ID of flow :  " << endl;
						cin >> flowID;
						jmethodID mid2 = env->GetStaticMethodID(cls2, "flowDescription", "()V");
						env->CallStaticVoidMethod(cls2, mid2, (jint)flowID);
					}

					else if (flowSelect == 2) {
						jmethodID mid2 = env->GetStaticMethodID(cls2, "flowsOwned", "()V");
						env->CallStaticVoidMethod(cls2, mid2);
					}

					else if (flowSelect == 3) {
						cout << "Enter the name of Flow : " << endl;
						getline(cin, flowCheckName);
						cout << "Enter the version of Flow : " << endl;
						getline(cin, flowCheckVersion);

						jstring jStringParam1 = env->NewStringUTF(flowCheckName.c_str());
						jstring jStringParam2 = env->NewStringUTF(flowCheckVersion.c_str());
						jmethodID mid2 = env->GetStaticMethodID(cls2, "flowsExists", "(Ljava/lang/String;Ljava/lang/String;)V");
						env->CallStaticVoidMethod(cls2, mid2, jStringParam1, jStringParam2);
						env->DeleteLocalRef(jStringParam1);
						env->DeleteLocalRef(jStringParam2);
					}
					else if (flowSelect == 4) {
						int flowIDRemove;
						cout << "Enter the flow ID to delete : " << endl;
						cin >> flowIDRemove;
						jmethodID mid2 = env->GetStaticMethodID(cls2, "Removeflow", "()V");
						env->CallStaticVoidMethod(cls2, mid2, (jint)flowIDRemove);
					}

					else if (flowSelect == 5) {
						
						string flowDescription;
						string CodeJar;
						string sourceZip;
						
						cout << "Enter the description of Flow : " << endl;
						cin >> flowDescription;
						cout << "Enter the CodeJar File of Flow : " << endl;
						cin >> CodeJar;
						cout << "Enter the sourceZip File of Flow : " << endl;
						cin >> sourceZip;
						
						jstring jStringParam1 = env->NewStringUTF(flowDescription.c_str());
						jstring jStringParam2 = env->NewStringUTF(CodeJar.c_str());
						jstring jStringParam3 = env->NewStringUTF(sourceZip.c_str());
						jmethodID mid2 = env->GetStaticMethodID(cls2, "Uploadflow", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
						env->CallStaticVoidMethod(cls2, mid2, jStringParam1, jStringParam2, jStringParam3);
						env->DeleteLocalRef(jStringParam1);
						env->DeleteLocalRef(jStringParam2);
						env->DeleteLocalRef(jStringParam3);
					}

					char check2;
					cout << "\n Do you want to checkout anyother dataSet ? : Y or N : :" << endl;
					cin >> check2;
					if (check2 != 'Y') {
						check = false;
					}
				}
			}

			else if (selection == 5) {
				int taskId;
				bool check = true;
				int taskCheck;
				while (check) {
					cout << "Press 1 to Retrieve the description of Task :  " << endl;
					cout << "Press 2 to Retrieve all evaluations for the task over the specified window of the stream :  " << endl;
					cin >> taskCheck;

					if (taskCheck == 1) {
						cout << "Please enter the ID of task : " << endl;
						cin >> taskId;
						// Check out and try this function in Java.And print result
						jmethodID mid2 = env->GetStaticMethodID(cls2, "taskDescription", "()V");
						env->CallStaticVoidMethod(cls2, mid2, (jint)taskId);
					}

					else if (taskCheck == 2) {
						jmethodID mid2 = env->GetStaticMethodID(cls2, "taskEvaluations", "()V");
						env->CallStaticVoidMethod(cls2, mid2);
					}
					char check2;
					cout << "\n Do you want to checkout anyother task ? : Y or N : :" << endl;
					cin >> check2;
					if (check2 != 'Y') {
						check = false;
					}
				}
			}

			else if (selection == 6) {

				int runId;
				bool check = true;
				int runCheck;
				while (check) {
					cout << "Press 1 to Retrieve the description of Run : " << endl;
					cout << "Press 2 to Deletes the Run : " << endl;
					cout << "Press 3 to  Upload a Run to OpenML : " << endl;
					cin >> runCheck;

					if (runCheck == 1) {
						cout << "Please enter the ID of Run : " << endl;
						cin >> runId;
						// Check out and try this function in Java.And print result
						jmethodID mid2 = env->GetStaticMethodID(cls2, "runDescription", "()V");
						env->CallStaticVoidMethod(cls2, mid2, (jint)runId);
					}

					else if (runCheck == 2) {
						cout << "Please enter the ID of Run to Delete : " << endl;
						cin >> runId;
						jmethodID mid2 = env->GetStaticMethodID(cls2, "runDelete", "()V");
						env->CallStaticVoidMethod(cls2, mid2, (jint)runId);
					}

					else if (runCheck == 3) {
						string firstTag;
						string secondTag;
						string fileName;

						cout << "Enter the First Tag : " << endl;
						cin >> firstTag;
						cout << "Enter the Second Tag : " << endl;
						cin >> secondTag;
						cout << "Enter the enter the name of file with extension and please place it inside uploads folder :  " << endl;
						cin >> filename;

						jstring jStringParam1 = env->NewStringUTF(firstTag.c_str());
						jstring jStringParam2 = env->NewStringUTF(secondTag.c_str());
						jstring jStringParam3 = env->NewStringUTF(filename.c_str());
						jmethodID mid2 = env->GetStaticMethodID(cls2, "runUpload", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
						env->CallStaticVoidMethod(cls2, mid2, jStringParam1, jStringParam2, jStringParam3);
						env->DeleteLocalRef(jStringParam1);
						env->DeleteLocalRef(jStringParam2);
						env->DeleteLocalRef(jStringParam3);
					}
					char check2;
					cout << "\n Do you want to checkout anyother RUN ? : Y or N : :" << endl;
					cin >> check2;
					if (check2 != 'Y') {
						check = false;
					}
				}
			}

			char mainCheck2;
			cout << "\n Do you want to checkout anyother RUN ? : Y or N : :" << endl;
			cin >> mainCheck2;
			if (mainCheck2 != 'Y') {
				mainCheck = false;
			}
		}
	}
	// End JAVA calls ==================================================================


	jvm->DestroyJavaVM();

	cout << "Press any key...";
	cin.get();
}