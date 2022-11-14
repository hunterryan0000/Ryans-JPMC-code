let should = chai.should();

describe('Exercises', () => {

  describe('isAdmitted', () => {
    it('should accept anyone with at least a 4.0 gpa', () => {
      isAdmitted(4.0, 0, false).should.be.true;
    });
    it('should accept anyone with at least 1300 SAT score', () => {
      isAdmitted(1, 1300, false).should.be.true;
    });
    it('should accept anyone with at least a 3.0 gpa and recommendation', () => {
      isAdmitted(3.0, 0, true).should.be.true;
    });
    it('should accept anyone with at least 1200 SAT score and recommendation', () => {
      isAdmitted(1, 1200, true).should.be.true;
    });
    it('should reject gpa below 3.0 with recommendation', () => {
      isAdmitted(2.9, 100, true).should.be.false;
    });
    it('should reject SAT below 1200 even with recommendation', () => {
      isAdmitted(1, 1199, true).should.be.false;
    });
  });

  describe('isPhysicallyFit', () => {
    it('should pass if mileInMinutes is 10 or less', () => {
      isPhysicallyFit(0, 0, 10).should.be.true;
      isPhysicallyFit(0, 0, 9).should.be.true;
    });
    it('should pass with 50 or more pushups and 50 or more situps regardless of mileInMinutes', () => {
      isPhysicallyFit(50, 50, 300).should.be.true;
      isPhysicallyFit(60, 60, 300).should.be.true;
    });
    it('should pass with 60 or more situps and mileInMinutes is 12 or less regardless of pushups', () => {
      isPhysicallyFit(60, 0, 12).should.be.true;
      isPhysicallyFit(61, 0, 11).should.be.true;
    });
    it('should pass with 60 or more pushups and mileInMinutes is 14 or less regardless of situps', () => {
      isPhysicallyFit(0, 60, 14).should.be.true;
      isPhysicallyFit(0, 61, 13).should.be.true;
    });
    it('should fail if 11 minute mile, situps 59, pushups 49', () => {
      isPhysicallyFit(59, 49, 11).should.be.false;
    });
    it('should fail if 11 minute mile, situps 49, pushups 59', () => {
      isPhysicallyFit(49, 59, 11).should.be.false;
    });
  });

  describe('isKnockedOut', () => {
    it('should be true with more than 20 damage and luck of 0.5 or less', () => {
      isKnockedOut({ "damage": 20, "currentHealth": 40, "luck": 0.49 }).should.be.false;
      isKnockedOut({ "damage": 20, "currentHealth": 40, "luck": 0.5 }).should.be.false;
      isKnockedOut({ "damage": 21, "currentHealth": 40, "luck": 0.49 }).should.be.true;
      isKnockedOut({ "damage": 21, "currentHealth": 40, "luck": 0.5 }).should.be.true;
      isKnockedOut({ "damage": 21, "currentHealth": 40, "luck": 0.51 }).should.be.false;
    });
    it('should be true with at least as much damage as health, regardless of luck', () => {
      isKnockedOut({ "damage": 1, "currentHealth": 1, "luck": 0 }).should.be.true;
      isKnockedOut({ "damage": 1, "currentHealth": 1, "luck": 0.51 }).should.be.true;
      isKnockedOut({ "damage": 21, "currentHealth": 20, "luck": 0.51 }).should.be.true;
      isKnockedOut({ "damage": 19, "currentHealth": 19, "luck": 0 }).should.be.true;
      isKnockedOut({ "damage": 1, "currentHealth": 2, "luck": 0 }).should.be.false;

    });
  });

  describe('wordFlipper', () => {
    it('should return a string with no 5 letter words unaltered', () => {
      wordFlipper('one two four five').should.equal('one two four five');
    });
    it('should return a string with just 5 letter words in reverse', () => {
      wordFlipper('Isaac wants lemon water.').should.equal('caasI stnaw nomel .retaw')
    });
    it('should count numbers in a word toward length', () => {
      wordFlipper('one1 two2 four4 five5').should.equal('one1 two2 4ruof 5evif')
    });
    it('should count special characters in a word toward length', () => {
      wordFlipper('one1, two2, four4, five').should.equal(',1eno ,2owt ,4ruof five')
    });
  });

  describe('calculateTotal', () => {
    it('should return a basic sum if array length is not multiple of any num and no nums are multiples of 2', () => {
      calculateTotal([3, 3]).should.equal(6, '3 and 3 are not multiples of 2 nor is the length of the array a multiple of 3, so total is 3+3');
    });
    it('should add a value twice if it is a multiple of 2', () => {
      calculateTotal([2]).should.equal(4, '2 is a multiple of itself, but an array length of 1 is not a multiple of 2, so it is added twice, 2+2');
    });
    it('should subtract a value if the length of the array is a multiple of the value', () => {
      calculateTotal([3, 1]).should.equal(2, 'neither condition applies to 3, but the array length is a multiple of 1, so 1 is subtracted instead, 3-1');
    });
    it('should subtract a value twice if the length of the array is a multiple of the value and it is a multiple of 2', () => {
      calculateTotal([3, 2]).should.equal(-1, 'neither condition applies to 3 but 2 is a multiple of itself and the array length is a multiple of 2, so it is subtracted twice, 3-2-2');
    });
  });

  describe('caesarCipher', () => {
    it('should shift all letters in a string by a given number of positions', () => {
      caesarCipher(2, 'jimothy anderson').should.equal('lkoqvja cpfgtuqp');
    });
    it('should maintain a character\'s casing in a string', () => {
      caesarCipher(3, 'Annie visits Idaho').should.equal('Dqqlh ylvlwv Lgdkr');
    });
    it('should not shift numbers in the string', () => {
      caesarCipher(4, '123 S Fake Street').should.equal('123 W Jeoi Wxviix');
    });
    it('should only shift characters Aa-Zz in the string', () => {
      caesarCipher(5, 'Do the D-A-N-C-E 1234, fight!').should.equal('It ymj I-F-S-H-J 1234, knlmy!');
    });
  });

  describe('scheduleArrivals', () => {
    it('should determine whether a train is on schedule', () => {
      const trains = [
        {
          "name": 'UPacific',
          "estimatedTripTime": 300,
          "avgSpeed": 30,
          "routeDistance": 150,
          "distanceRemaining": 120,
        },
        {
          "name": 'B&O',
          "estimatedTripTime": 240,
          "avgSpeed": 30,
          "routeDistance": 150,
          "distanceRemaining": 120,
        },
      ]
      const result = orderArrivals(trains);
      result[0].isOnSchedule.should.equal(true, 'checking that the first train is arriving on schedule');
      result[1].isOnSchedule.should.equal(false, 'checking that the second train is not arriving on schedule');
    });
    it('should order the return object from least timeRemaining to most', () => {
      const trains = [
        {
          "name": 'UPacific',
          "estimatedTripTime": 300,
          "avgSpeed": 30,
          "routeDistance": 150,
          "distanceRemaining": 120,
        },
        {
          "name": 'Bullet1',
          "estimatedTripTime": 300,
          "avgSpeed": 150,
          "routeDistance": 150,
          "distanceRemaining": 120,
        },
        {
          "name": 'B&O',
          "estimatedTripTime": 300,
          "avgSpeed": 60,
          "routeDistance": 150,
          "distanceRemaining": 120,
        },
      ]
      const result = orderArrivals(trains);
      result[0].timeRemaining.should.equal(48, 'checking the first train\'s timeRemaining');
      result[0].timeRemaining.should.be.lessThan(result[1].timeRemaining, 'checking that the first train is arriving sooner than the second');
      result[0].timeRemaining.should.be.lessThan(result[2].timeRemaining, 'checking that the first train is arriving sooner than the third');

      result[1].timeRemaining.should.equal(120, 'checking the second train\'s timeRemaining');
      result[1].timeRemaining.should.be.lessThan(result[2].timeRemaining, 'checking that the second train is arriving sooner than the third');

      result[2].timeRemaining.should.equal(240, 'checking the third train\'s timeRemaining');
    });
  });

  describe('monsterRancher', () => {
    it('should return isSafe of true if numberOfMonsters reaches 0', () => {
      const result = captureMonsters({ "numberOfMonsters": 1, "numberOfTraps": 3, "distance": 1 });
      result.monstersRemaining.should.equal(0, 'checking that the monster has been removed');
      result.isSafe.should.equal(true, 'checking that the expedition is marked safe');
    });
    it('should return isSafe of false if distance or traps run out', () => {
      const notEnoughDistance = captureMonsters({ "numberOfMonsters": 2, "numberOfTraps": 3, "distance": 1 });
      notEnoughDistance.monstersRemaining.should.be.greaterThan(0, 'checking that monsters remain');
      notEnoughDistance.distanceRemaining.should.equal(0, 'checking that distance has run out');
      notEnoughDistance.isSafe.should.equal(false, 'checking that the expedition is marked not safe');

      const notEnoughTraps = captureMonsters({ "numberOfMonsters": 2, "numberOfTraps": 1, "distance": 1 });
      notEnoughTraps.monstersRemaining.should.be.greaterThan(0, 'checking that monsters remain');
      notEnoughTraps.trapsRemaining.should.equal(0, 'checking that traps have run out');
      notEnoughTraps.isSafe.should.equal(false, 'checking that the expedition is marked not safe');

    });
    it('should capture a monster before distance is reduced', () => {
      const result = captureMonsters({ "numberOfMonsters": 1, "numberOfTraps": 3, "distance": 1 });
      result.monstersRemaining.should.equal(0, 'checking that the monster has been removed');
      result.distanceRemaining.should.equal(1, 'checking that the distance has not been reduced');
    });
    it('should return result before removing another monster/trap if distance reaches 0', () => {
      const result = captureMonsters({ "numberOfMonsters": 3, "numberOfTraps": 3, "distance": 3 });
      result.monstersRemaining.should.equal(1, 'checking that a monster remains');
      result.trapsRemaining.should.equal(1, 'checking that a trap remains');
      result.distanceRemaining.should.equal(0, 'checking that distance has run out');
    });
    it('should not return a negative distance if monsters reach you', () => {
      const result = captureMonsters({ "numberOfMonsters": 4, "numberOfTraps": 3, "distance": 4 });
      result.distanceRemaining.should.equal(0, 'checking that distance has not become less than 0');
    });
  });

  describe('characterCount', () => {
    it('should return an object with characters as keys and counts as vals', () => {
      const result = characterCount('yep')
      result.y.should.equal(1, 'checking count for y');
      result.e.should.equal(1, 'checking count for e');
      result.p.should.equal(1, 'checking count for p');
    });
    it('should not be case sensitive', () => {
      const result = characterCount('Bobbert Toddson');
      result.should.not.have.property('B', 'checking that B is not counted in uppercase');
      result.b.should.equal(3, 'checking that B is still included in count in lowercase');
      result.should.not.have.property('T', 'checking that T is not counted in uppercase');
      result.t.should.equal(2, 'checking that T is still included in count in lowercase');
    });
    it('should not include spaces in count', () => {
      const result = characterCount('b b   b');
      result.should.not.have.property(' ', 'checking that spaces are not counted');
      result.b.should.equal(3, 'checking that all b\'s are counted');
    });
    it('should include numbers in count', () => {
      const result = characterCount('1211');
      result['1'].should.equal(3, 'checking count for 1');
      result['2'].should.equal(1, 'checking count for 2');
    });
    it('should include special characters in count', () => {
      const result = characterCount('!#@$?');
      result.should.deep.equal({ '!': 1, '#': 1, '@': 1, '$': 1, '?': 1 }, 'checking count for several special characters');
    });
  });
});