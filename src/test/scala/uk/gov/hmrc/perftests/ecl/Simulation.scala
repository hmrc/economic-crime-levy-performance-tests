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

package uk.gov.hmrc.perftests.ecl

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.ecl.requests.AccountRequest._
import uk.gov.hmrc.perftests.ecl.requests.AmendRegistrationRequests._
import uk.gov.hmrc.perftests.ecl.requests.AmendReturnsRequests._
import uk.gov.hmrc.perftests.ecl.requests.AuthRequests._
import uk.gov.hmrc.perftests.ecl.requests.EnrolmentRequests._
import uk.gov.hmrc.perftests.ecl.requests.RegistrationRequests._
import uk.gov.hmrc.perftests.ecl.requests.ReturnsRequests._

class Simulation extends PerformanceTestRunner {

  setup("ecl-registration-login", "Login via Auth Wizard for registration").withRequests(
    navigateToRegisterAuthWizard,
    navigateToAuthWizardRedirectionUrl,
    submitRegisterAuthWizardForm()
  )
  setup("ecl-registration-journey", "Register for ECL").withActions(
    navigateToWhetherOrNotAmlActivityStartedInCurrentYear,
    submitWhetherOrNotAmlActivityStartedInCurrentYear("true"),
    navigateToAmlSupervisor,
    submitAmlSupervisor("Hmrc"),
    navigateToRelevantAccountingPeriod,
    submitRelevantAccountingPeriod("true"),
    navigateToUkRevenueForAccountingPeriod,
    submitUkRevenueForAccountingPeriod("10200000"),
    navigateToEclLiableForPreviousFinancialYear,
    submitEclLiableForPreviousFinancialYear("true"),
    navigateToEntityType,
    submitEntityType("UkLimitedCompany"),
    navigateToStubGrsJourneyData,
    submitStubGrsJourneyData(),
    navigateToGrsContinue,
    navigateToWhatIsYourBusinessSector,
    submitWhatIsYourBusinessSector("Auditor"),
    navigateToFirstContactPersonName,
    submitFirstContactPersonName("Jim"),
    navigateToFirstContactPersonRole,
    submitFirstContactPersonRole("Director"),
    navigateToFirstContactPersonEmail,
    submitFirstContactPersonEmail("test@test.com"),
    navigateToFirstContactPersonTelephone,
    submitFirstContactPersonTelephone("07291722133"),
    navigateToWouldYouLikeToAddAnotherContact,
    submitWouldYouLikeToAddAnotherContact("true"),
    navigateToSecondContactPersonName,
    submitSecondContactPersonName("Tim"),
    navigateToSecondContactPersonRole,
    submitSecondContactPersonRole("Accounts Officer"),
    navigateToSecondContactPersonEmail,
    submitSecondContactPersonEmail("verify@verify.com"),
    navigateToSecondContactPersonTelephone,
    submitSecondContactPersonTelephone("0175344171"),
    navigateToRegisteredContactAddress,
    submitRegisteredContactAddress("true"),
    navigateToSubmitCheckYourAnswers,
    submitCheckYourAnswers(),
    navigateToRegistrationSubmitted
  )
  setup("ecl-returns-login", "Log in via Auth Wizard for returns").withRequests(
    navigateToReturnAuthWizard,
    submitReturnsAuthWizardForm()
  )
  setup("ecl-returns-journey", "ECL Returns").withActions(
    navigateToStartReturn,
    navigateToIsRelevantAccountingPeriod12MonthsUrl,
    submitIsYourRelevantAccountingPeriod12Months("true"),
    navigateToUkRevenueAccountingPeriod,
    submitUkRevenueForTheRelevantAccountingPeriod("10200000"),
    navigateToAmlRegulatedActivity,
    submitAmlRegulatedActivity("true"),
    navigateToAmountDue,
    submitAmountDue(),
    navigateToContactName,
    submitContactName("James V"),
    navigateToContactRole,
    submitContactRole("Account Officer"),
    navigateToContactEmailAddress,
    submitContactEmailAddress("test@oc.com"),
    navigateToContactTelephone,
    submitContactTelephone("01475344272"),
    navigateToCheckYourAnswers,
    submitCheckYourAnswersForReturns(),
    navigateToReturnSubmitted
  )
  setup("ecl-enrolment-journey", "Claim enrolment for ECL").withRequests(
    navigateToEnrolmentAuthWizard,
    submitEnrolmentAuthWizardForm(),
    navigateToDoYouHaveAnEclReferenceNumber,
    submitDoYouHaveAnEclReferenceNumber("Yes"),
    navigateToYourEclReferenceNumber,
    submitYourEclReferenceNumber("XMECL0000000001"),
    navigateToEclRegistrationDate,
    submitYourEclRegistrationDate("01", "03", "2023")
  )
  setup("ecl-account-journey", "Account journey for ECL").withRequests(
    navigateToAccountAuthWizard,
    submitAccountAuthWizardForm(),
    navigateToYourEclReturns,
    navigateToSpecificReturn()
  )
  setup("ecl-amend-registration-journey", "Amend Registration for ECL").withRequests(
    navigateToAccountAuthWizard,
    submitAccountAuthWizardForm(),
    navigateToEclAccount,
    navigateToSubmitAmendRegistration,
    navigateToReasonForAmendRegistration,
    submitReasonForAmendRegistration("Revenue changed for the current FY"),
    navigateToAmendAmlSupervisor,
    submitAmendAmlSupervisor("Hmrc"),
    navigateToYourAmendBusinessSector,
    submitYourAmendBusinessSector("Auditor"),
    navigateToAmendFirstContactPersonName,
    submitAmendFirstContactPersonName("James"),
    navigateToAmendFirstContactPersonRole,
    submitAmendFirstContactPersonRole("Compliance Office"),
    navigateToAmendFirstContactPersonEmail,
    submitAmendFirstContactPersonEmail("test@oc.com"),
    navigateToAmendFirstContactPersonTelephone,
    submitAmendFirstContactPersonTelephone("07291722122"),
    navigateToAmendWouldYouLikeToAddAnotherContact,
    submitAmendWouldYouLikeToAddAnotherContact("true"),
    navigateToAmendSecondContactPersonName,
    submitAmendSecondContactPersonName("Jon"),
    navigateToAmendSecondContactPersonRole,
    submitAmendSecondContactPersonRole("Accounts Director"),
    navigateToAmendSecondContactPersonEmail,
    submitAmendSecondContactPersonEmail("verify@oc.com"),
    navigateToAmendSecondContactPersonTelephone,
    submitAmendSecondContactPersonTelephone("0175344232"),
    navigateToWhetherOrNotContactAddressInUk,
    submitWhetherOrNotContactAddressInUk("true"),
    navigateToStubAlfJourneyData,
    submitStubAlfJourneyData(),
    navigateToAlfContinue,
    navigateToAmendSubmitCheckYourAnswers,
    submitAmendCheckYourAnswers(),
    navigateToAmendRegistrationSubmitted
  )
  setup("ecl-amend-returns-journey", "Amend ECL Returns").withRequests(
    navigateToAccountAuthWizard,
    submitAccountAuthWizardForm(),
    navigateToYourEclReturns,
    navigateToAmendStartReturn,
    navigateToReasonForAmendReturn,
    submitReasonForAmendReturns("Revenue changed for the current FY"),
    navigateToAmendIsRelevantAccountingPeriod12MonthsUrl,
    submitAmendIsYourRelevantAccountingPeriod12Months("true"),
    navigateToAmendUkRevenueAccountingPeriod,
    submitAmendUkRevenueForTheRelevantAccountingPeriod("10200000"),
    navigateToAmendAmlRegulatedActivity,
    submitAmendAmlRegulatedActivity("true"),
    navigateToAmendAmountDue,
    submitAmendAmountDue(),
    navigateToAmendContactName,
    submitAmendContactName("Dan D"),
    navigateToAmendContactRole,
    submitAmendContactRole("Director"),
    navigateToAmendContactEmailAddress,
    submitAmendContactEmailAddress("verify@oc.com"),
    navigateToAmendContactTelephone,
    submitAmendContactTelephone("01475322272"),
    navigateToAmendCheckYourAnswers,
    submitAmendCheckYourAnswersForReturns(),
    navigateToAmendReturnSubmitted
  )
  runSimulation()
}
