/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.perftests.ecl.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.perftests.ecl.Configuration
import uk.gov.hmrc.perftests.ecl.requests.AuthRequests._

object RegistrationRequests extends Configuration {

  def redirectLocation(relativeLocation: String): String =
    s"$registrationUrl/$relativeLocation"

  val amlRegulatedActivityUrl: String            = s"$registerAuthWizardUrl/aml-regulated-activity-question"
  val whoIsYourAmlSupervisorUrl: String          = s"$registerAuthWizardUrl/your-aml-supervisor/Initial"
  val relevantAccountingPeriodUrl: String        = s"$registerAuthWizardUrl/accounting-period-question"
  val ukRevenueForAccountingPeriodUrl: String    = s"$registerAuthWizardUrl/uk-revenue-in-accounting-period"
  val previousFinancialYearUrl: String           = s"$registerAuthWizardUrl/previous-financial-year?fromRevenuePage=true"
  val whatIsYourEntityType: String               = s"$registerAuthWizardUrl/what-is-your-entity-type"
  val stubGrsJourneyDataUrl: String              =
    s"$registerAuthWizardUrl/test-only/stub-grs-journey-data?continueUrl=normalmode&entityType=UkLimitedCompany"
  val whatIsYourBusinessSectorUrl: String        = s"$registerAuthWizardUrl/what-is-your-business-sector"
  val firstContactPersonNameUrl: String          = s"$registerAuthWizardUrl/contact-name"
  val firstContactPersonRoleUrl: String          = s"$registerAuthWizardUrl/contact-role"
  val firstContactPersonEmailUrl: String         = s"$registerAuthWizardUrl/contact-email-address"
  val firstContactPersonTelephoneUrl: String     = s"$registerAuthWizardUrl/contact-telephone"
  val wouldYouLikeToAddAnotherContactUrl: String = s"$registerAuthWizardUrl/second-contact"
  val secondContactPersonNameUrl: String         = s"$registerAuthWizardUrl/second-contact-name"
  val secondContactPersonRoleUrl: String         = s"$registerAuthWizardUrl/second-contact-role"
  val secondContactPersonEmailUrl: String        = s"$registerAuthWizardUrl/second-contact-email-address"
  val secondContactPersonTelephoneUrl: String    = s"$registerAuthWizardUrl/second-contact-telephone"
  val registeredContactAddressUrl: String        = s"$registerAuthWizardUrl/contact-address"
  val submitCheckYourAnswersUrl: String          = s"$registerAuthWizardUrl/check-your-answers"
  val registrationSubmittedUrl: String           = s"$registerAuthWizardUrl/registration-submitted"

  val navigateToWhetherOrNotAmlActivityStartedInCurrentYear: HttpRequestBuilder =
    http("Navigate to /did-you-carry-out-aml-regulated-activity")
      .get(amlRegulatedActivityUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitWhetherOrNotAmlActivityStartedInCurrentYear(answer: String): HttpRequestBuilder =
    http("Did you carry out AML regulated activity" + answer)
      .post(amlRegulatedActivityUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", answer)
      .check(status.is(303))

  val navigateToAmlSupervisor: HttpRequestBuilder =
    http("Navigate to /who-is-your-aml-supervisor")
      .get(whoIsYourAmlSupervisorUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAmlSupervisor(amlSupervisor: String): HttpRequestBuilder =
    http("Submit AML Supervisor: " + amlSupervisor)
      .post(whoIsYourAmlSupervisorUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", amlSupervisor)
      .formParam("otherProfessionalBody", "")
      .check(status.is(303))

  val navigateToRelevantAccountingPeriod: HttpRequestBuilder =
    http("Navigate to /is-relevant-accounting-period-12-months")
      .get(relevantAccountingPeriodUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitRelevantAccountingPeriod(answer: String): HttpRequestBuilder =
    http("Submit relevant accounting period: " + answer)
      .post(relevantAccountingPeriodUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", answer)
      .check(status.is(303))

  val navigateToUkRevenueForAccountingPeriod: HttpRequestBuilder =
    http("Navigate to /uk-revenue-for-accounting-period")
      .get(ukRevenueForAccountingPeriodUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitUkRevenueForAccountingPeriod(ukRevenue: String): HttpRequestBuilder =
    http("Submit UK Revenue for relevant accounting period: " + ukRevenue)
      .post(ukRevenueForAccountingPeriodUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", ukRevenue)
      .check(status.is(303))

  val navigateToPreviousFinancialYear: HttpRequestBuilder =
    http("Navigate to /previous-financial-year")
      .get(previousFinancialYearUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitLevyForPreviousFinancialYear(answer: String): HttpRequestBuilder =
    http("Liable for Previous Financial Year: " + answer)
      .post(previousFinancialYearUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", answer)
      .check(status.is(303))

  val navigateToEntityType: HttpRequestBuilder =
    http("Navigate to /what-is-your-entity-type")
      .get(whatIsYourEntityType)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitEntityType(entityType: String): HttpRequestBuilder =
    http("Submit Entity Type: " + entityType)
      .post(whatIsYourEntityType)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", entityType)
      .check(status.is(303))

  val navigateToStubGrsJourneyData: HttpRequestBuilder =
    http("Navigate to /test-only/stub-grs-journey-data")
      .get(stubGrsJourneyDataUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  val navigateToGrsContinue: HttpRequestBuilder =
    http("Navigate to /grs-continue/normalmode")
      .get(redirectLocation("${grsContinueLocation}"))
      .check(status.is(303))

  def submitStubGrsJourneyData(): HttpRequestBuilder =
    http("Stub GRS Journey Data")
      .post(stubGrsJourneyDataUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam(
        "grsJourneyDataJson",
        """{
            "companyProfile" : {
              "companyName" : "Test Company Name",
              "companyNumber" : "01234567",
              "dateOfIncorporation" : "2007-12-03",
              "unsanitisedCHROAddress" : {
                "address_line_1" : "Test Address Line 1",
                "address_line_2" : "Test Address Line 2",
                "country" : "United Kingdom",
                "locality" : "Test Town",
                "postal_code" : "AB1 2CD",
                "region" : "Test Region"
              }
            },
            "ctutr" : "1234567890",
            "identifiersMatch" : true,
            "registration" : {
              "registrationStatus" : "REGISTERED",
              "registeredBusinessPartnerId" : "X00000000000001"
            }
          }"""
      )
      .check(status.is(303))
      .check(header("Location").saveAs("grsContinueLocation"))

  val navigateToWhatIsYourBusinessSector: HttpRequestBuilder =
    http("Navigate to /what-is-your-business-sector")
      .get(whatIsYourBusinessSectorUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitWhatIsYourBusinessSector(businessSector: String): HttpRequestBuilder =
    http("Submit Business sector: " + businessSector)
      .post(whatIsYourBusinessSectorUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", businessSector)
      .check(status.is(303))

  val navigateToFirstContactPersonName: HttpRequestBuilder =
    http("Navigate to /contact-name")
      .get(firstContactPersonNameUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitFirstContactPersonName(contactName: String): HttpRequestBuilder =
    http("Submit contact person's Name: " + contactName)
      .post(firstContactPersonNameUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", contactName)
      .check(status.is(303))

  val navigateToFirstContactPersonRole: HttpRequestBuilder =
    http("Navigate to /contact-role")
      .get(firstContactPersonRoleUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitFirstContactPersonRole(contactRole: String): HttpRequestBuilder =
    http("Submit contact person's Role: " + contactRole)
      .post(firstContactPersonRoleUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", contactRole)
      .check(status.is(303))

  val navigateToFirstContactPersonEmail: HttpRequestBuilder =
    http("Navigate to /contact-email-address")
      .get(firstContactPersonEmailUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitFirstContactPersonEmail(emailAddress: String): HttpRequestBuilder =
    http("Submit contact person's Email address: " + emailAddress)
      .post(firstContactPersonEmailUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", emailAddress)
      .check(status.is(303))

  val navigateToFirstContactPersonTelephone: HttpRequestBuilder =
    http("Navigate to /contact-Telephone")
      .get(firstContactPersonTelephoneUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitFirstContactPersonTelephone(contactNumber: String): HttpRequestBuilder =
    http("Submit contact person's Telephone: " + contactNumber)
      .post(firstContactPersonTelephoneUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", contactNumber)
      .check(status.is(303))

  val navigateToWouldYouLikeToAddAnotherContact: HttpRequestBuilder             =
    http("Navigate to /second-contact")
      .get(wouldYouLikeToAddAnotherContactUrl)
      .check(status.is(200))
      .check(saveCsrfToken)
  def submitWouldYouLikeToAddAnotherContact(answer: String): HttpRequestBuilder =
    http("Submit add another contact: " + answer)
      .post(wouldYouLikeToAddAnotherContactUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", answer)
      .check(status.is(303))

  val navigateToSecondContactPersonName: HttpRequestBuilder =
    http("Navigate to /second-contact-name")
      .get(secondContactPersonNameUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitSecondContactPersonName(contactName: String): HttpRequestBuilder =
    http("Submit second contact person's Name: " + contactName)
      .post(secondContactPersonNameUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", contactName)
      .check(status.is(303))

  val navigateToSecondContactPersonRole: HttpRequestBuilder =
    http("Navigate to /second-contact-role")
      .get(secondContactPersonRoleUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitSecondContactPersonRole(contactRole: String): HttpRequestBuilder =
    http("Submit second contact person's Role: " + contactRole)
      .post(secondContactPersonRoleUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", contactRole)
      .check(status.is(303))

  val navigateToSecondContactPersonEmail: HttpRequestBuilder =
    http("Navigate to /second-contact-email-address")
      .get(secondContactPersonEmailUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitSecondContactPersonEmail(emailAddress: String): HttpRequestBuilder =
    http("Submit second contact person's Email address: " + emailAddress)
      .post(secondContactPersonEmailUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", emailAddress)
      .check(status.is(303))

  val navigateToSecondContactPersonTelephone: HttpRequestBuilder =
    http("Navigate to /second-contact-Telephone")
      .get(secondContactPersonTelephoneUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitSecondContactPersonTelephone(contactNumber: String): HttpRequestBuilder =
    http("Submit second contact person's Telephone: " + contactNumber)
      .post(secondContactPersonTelephoneUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", contactNumber)
      .check(status.is(303))

  val navigateToRegisteredContactAddress: HttpRequestBuilder =
    http("Navigate to /contact-address")
      .get(registeredContactAddressUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitRegisteredContactAddress(answer: String): HttpRequestBuilder =
    http("Submit registered contact address: " + answer)
      .post(registeredContactAddressUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", answer)
      .check(status.is(303))

  val navigateToSubmitCheckYourAnswers: HttpRequestBuilder =
    http("Navigate to /check-your-answers")
      .get(submitCheckYourAnswersUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitCheckYourAnswers(): HttpRequestBuilder =
    http("Submit check your answers")
      .post(submitCheckYourAnswersUrl)
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToRegistrationSubmitted: HttpRequestBuilder =
    http("Navigate to /registration-submitted")
      .get(registrationSubmittedUrl)
      .check(status.is(200))

}
