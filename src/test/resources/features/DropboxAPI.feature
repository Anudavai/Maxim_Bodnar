Feature: Dropbox API File test

  Scenario Outline: File uploading
    Given I have a file "<filename>"
    When I upload file "<filename>" to Dropbox
    Then I see that the file "<filename>" has been uploaded successfully
    Examples:
    |         filename |
    |    TestText1.txt |
    |    TestText2.txt |
    |   TestImage1.jpg |
    |   TestImage2.jpg |

  Scenario Outline: Getting file metadata
    Given The file "<filename>" is uploaded
    When I request metadata of the file "<filename>"
    Then I receive metadata of the file "<filename>"
    Examples:
      |         filename |
      |    TestText1.txt |
      |    TestText2.txt |
      |   TestImage1.jpg |
      |   TestImage2.jpg |

  Scenario Outline: Deleting the file
    Given The file "<filename>" is uploaded
    When I ask to delete file "<filename>"
    Then I see file "<filename>" is successfully deleted
    Examples:
      |         filename |
      |    TestText1.txt |
      |    TestText2.txt |
      |   TestImage1.jpg |
      |   TestImage2.jpg |
