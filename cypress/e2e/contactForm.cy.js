describe('Form Validation Test', () => {
    beforeEach(() => {
      cy.visit('/contact')
    })
  
    it('Should disable the submit button if form is invalid', () => {
      cy.get('form').within(() => {
        cy.get('input[type="text"]').eq(0).type('John')
        cy.get('input[type="text"]').eq(1).type('invalid-email')
        cy.get('textarea').type('This is a test feedback message')
        cy.get('button[type="submit"]').should('be.disabled')
      })
    })
  
    it('Should enable the submit button if form is valid', () => {
      cy.get('form').within(() => {
        cy.get('input[type="text"]').eq(0).type('John')
        cy.get('input[type="text"]').eq(1).type('john.doe@example.com')
        cy.get('textarea').type('This is a test feedback message')
        cy.get('button[type="submit"]').should('not.be.disabled')
      })
    })
  })
  
  describe('Contact Form', () => {
    it('Submits the form with valid data and receives a success message', () => {
      cy.visit('/contact')
      cy.get('input[type="text"]').eq(0).type('John')
      cy.get('input[type="text"]').eq(1).type('john.doe@example.com')
      cy.get('textarea').type('This is a test feedback message')
      cy.intercept("POST", "http://localhost:3000/feedbacks", {
        statusCode: 201,
      }).as("formSubmission")
      cy.get('button[type="submit"]').click()
      cy.wait('@formSubmission').then((interception) => {
        expect(interception.response.statusCode).to.equal(201);
      });
      cy.get('h5').should('have.text', 'Feedback has been sent!').wait(5000);
    })
  })
  