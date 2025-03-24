CREATE TABLE
    "users" (
        "user_id" INTEGER NOT NULL,
        "username" VARCHAR(255) NOT NULL,
        "email" VARCHAR(255) NOT NULL,
        "password_hash" TEXT NOT NULL,
        "role" TEXT NULL,
        "created_at" TIMESTAMP(0) WITHOUT TIME ZONE NULL DEFAULT CURRENT_TIMESTAMP,
        "updated_at" TIMESTAMP(0) WITHOUT TIME ZONE NULL DEFAULT CURRENT_TIMESTAMP
    );

ALTER TABLE "users" ADD PRIMARY KEY ("user_id");

ALTER TABLE "users" ADD CONSTRAINT "users_username_unique" UNIQUE ("username");

ALTER TABLE "users" ADD CONSTRAINT "users_email_unique" UNIQUE ("email");

CREATE TABLE
    "patients" (
        "patient_id" VARCHAR(10) NOT NULL,
        "name" VARCHAR(100) NOT NULL,
        "dob" DATE NOT NULL,
        "gender" VARCHAR(255) NOT NULL,
        "contact" VARCHAR(15) NOT NULL,
        "husband_name" VARCHAR(255) NULL,
        "father_name" VARCHAR(255) NULL,
        "address" TEXT NULL,
        "admission_date" DATE NOT NULL,
        "created_at" TIMESTAMP(0) WITHOUT TIME ZONE NULL DEFAULT CURRENT_TIMESTAMP
    );

ALTER TABLE "patients" ADD PRIMARY KEY ("patient_id");

CREATE TABLE
    "births" (
        "birth_id" VARCHAR(10) NOT NULL,
        "patient_id" VARCHAR(10) NOT NULL,
        "delivery_date" DATE NOT NULL,
        "status" VARCHAR(20) NOT NULL,
        "updated_at" TIMESTAMP(0) WITHOUT TIME ZONE NULL DEFAULT CURRENT_TIMESTAMP
    );

ALTER TABLE "births" ADD PRIMARY KEY ("birth_id");

CREATE TABLE
    "children" (
        "child_id" VARCHAR(10) NOT NULL,
        "birth_id" VARCHAR(10) NOT NULL,
        "certificate_id" VARCHAR(255) NOT NULL,
        "child_name" VARCHAR(100) NOT NULL,
        "gender" VARCHAR(20) NOT NULL,
        "weight" DECIMAL(4, 2) NOT NULL,
        "dob" DATE NOT NULL,
        "tob" TIME(0) WITHOUT TIME ZONE NOT NULL,
        "father_name" VARCHAR(255) NOT NULL,
        "mother_name" VARCHAR(255) NOT NULL
    );

ALTER TABLE "children" ADD PRIMARY KEY ("child_id");

CREATE TABLE
    "certificate_types" (
        "certificate_type_id" INTEGER NOT NULL,
        "type_name" VARCHAR(100) NOT NULL,
        "description" TEXT NULL
    );

ALTER TABLE "certificate_types" ADD PRIMARY KEY ("certificate_type_id");

ALTER TABLE "certificate_types" ADD CONSTRAINT "certificate_types_type_name_unique" UNIQUE ("type_name");

CREATE TABLE
    "certificates" (
        "certificate_id" VARCHAR(10) NOT NULL,
        "certificate_type_id" INTEGER NULL,
        "issued_date" DATE NOT NULL,
        "file_path" TEXT NOT NULL,
        "created_at" TIMESTAMP(0) WITHOUT TIME ZONE NULL DEFAULT CURRENT_TIMESTAMP
    );

ALTER TABLE "certificates" ADD PRIMARY KEY ("certificate_id");

CREATE TABLE
    "patient_certificates" (
        "patient_id" VARCHAR(10) NOT NULL,
        "certificate_id" VARCHAR(10) NOT NULL,
        PRIMARY KEY ("patient_id", "certificate_id")
    );

CREATE TABLE
    "birth_certificates" (
        "certificate_id" VARCHAR(10) NOT NULL,
        "birth_weight" DECIMAL(4, 2) NULL,
        "time_of_birth" TIME(0) WITHOUT TIME ZONE NOT NULL,
        "father_name" VARCHAR(255) NOT NULL,
        "mother_name" VARCHAR(255) NOT NULL
    );

ALTER TABLE "birth_certificates" ADD PRIMARY KEY ("certificate_id");

CREATE TABLE
    "vaccination_certificates" (
        "certificate_id" VARCHAR(10) NOT NULL,
        "vaccine_name" VARCHAR(255) NOT NULL,
        "dose_number" INTEGER NOT NULL,
        "next_due_date" DATE NULL
    );

ALTER TABLE "vaccination_certificates" ADD PRIMARY KEY ("certificate_id");

CREATE TABLE
    "lab_reports" (
        "certificate_id" VARCHAR(10) NOT NULL,
        "test_type" VARCHAR(255) NOT NULL,
        "test_results" TEXT NOT NULL
    );

ALTER TABLE "lab_reports" ADD PRIMARY KEY ("certificate_id");

ALTER TABLE "certificates" ADD CONSTRAINT "certificates_certificate_id_foreign" FOREIGN KEY ("certificate_id") REFERENCES "vaccination_certificates" ("certificate_id");

ALTER TABLE "certificates" ADD CONSTRAINT "certificates_certificate_type_id_foreign" FOREIGN KEY ("certificate_type_id") REFERENCES "certificate_types" ("certificate_type_id");

ALTER TABLE "certificates" ADD CONSTRAINT "certificates_certificate_id_foreign" FOREIGN KEY ("certificate_id") REFERENCES "birth_certificates" ("certificate_id");

ALTER TABLE "children" ADD CONSTRAINT "children_birth_id_foreign" FOREIGN KEY ("birth_id") REFERENCES "births" ("birth_id");

ALTER TABLE "certificates" ADD CONSTRAINT "certificates_certificate_id_foreign" FOREIGN KEY ("certificate_id") REFERENCES "lab_reports" ("certificate_id");

ALTER TABLE "patients" ADD CONSTRAINT "patients_patient_id_foreign" FOREIGN KEY ("patient_id") REFERENCES "patient_certificates" ("patient_id");

ALTER TABLE "births" ADD CONSTRAINT "births_patient_id_foreign" FOREIGN KEY ("patient_id") REFERENCES "patients" ("patient_id");

ALTER TABLE "certificates" ADD CONSTRAINT "certificates_certificate_id_foreign" FOREIGN KEY ("certificate_id") REFERENCES "patient_certificates" ("certificate_id");