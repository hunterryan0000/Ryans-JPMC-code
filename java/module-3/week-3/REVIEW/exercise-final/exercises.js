/**
 * ACME Academy has received a large number of student applications. 
 * Write a function called isAdmitted to determine if a student meets the requirements to be admitted.
 * This function takes 3 parameters:
 *   gpa
 *   satScore
 *   recommendation
 * A student is admitted only if one of the following is true:
 * Their gpa is 4.0 or higher.
 * They score 1300 or more on the SAT.
 * Their gpa is 3.0 or higher and have a recommendation.
 * Their SAT score is 1200 or higher and they have a recommendation.
 *
 * @param {number} gpa the GPA of the student
 * @param {number} satScore the student's SAT score
 * @param {boolean} recommendation does the student have a recommendation
 * @returns {boolean} true if they are admitted
 */
function isAdmitted(gpa, satScore, recommendation) {
  if (gpa >= 4.0 || satScore >= 1300) {
    return true;
  } else if (recommendation) {
    if (gpa >= 3.0 || satScore >= 1200) {
      return true;
    }
  }

  return false;
}

/**
 * Write a function called isPhysicallyFit
 * It determines whether or not someone passes gitFit's physical fitness test.
 
 * The function takes three parameters: situps, pushups, and mileInMinutes
 
 * They pass the test if:
 * They can run a mile in 10 minutes or less.
 * They can perform at least 50 situps and pushups.
 * They can perform 60 situps and run a mile in 12 minutes or less.
 * They can perform 60 pushups and run a mile in 14 minutes or less.
 * 
 * @param {number} situps 
 * @param {number} pushups
 * @param {number} mileInMinutes
 * @returns {boolean} true if they pass
 */
function isPhysicallyFit(situps, pushups, mileInMinutes) {
  if (mileInMinutes <= 10 || situps >= 50 && pushups >= 50) {
    return true;
  } else if (mileInMinutes <= 12 && situps >= 60) {
    return true;
  } else if (mileInMinutes <= 14 && pushups >= 60) {
    return true;
  } else {
    return false;
  }
}

/**
 * You are developing a game and need to write a function to determine whether a character makes it through a round of combat or gets knocked out. 
 * 
 * Write a function called isKnockedOut
 * It takes an object with the following properties: 
 *   currentHealth
 *   damage
 *   luck
 * 
 * A character is knocked out if:
 * They receive more than 20 damage and luck is 0.5 or less.
 * They receive damage equal to or greater than their current health.
 *
 * 
 * @param {object} combatRoll
 * @returns {boolean} true if they're knocked out
 */
function isKnockedOut(combatRoll) {
  if (combatRoll.damage > 20 && combatRoll.luck <= 0.50) {
    return true;
  } else if (combatRoll.damage >= combatRoll.currentHealth) {
    return true;
  } else {
    return false;
  }
}

/**
 * Did you know the average english word length is 4.7 characters?
 * To help visualize which words are "long", reverse all words that are five characters or longer in a given string.
 * 
 * Write a function called wordFlipper
 * It takes a string of one or more words, and returns the string, but with all words that are five letters or more reversed.
 * Treat special characters or punctuation as if they were just another letter.
 * 
 * Given the following string:
 * "I bless the rains down in Africa."
 * Your function should return:
 * "I sselb the sniar down in .acirfA"
 * 
 * @param {string} input
 * @returns {string}
 */
function wordFlipper(input) {
  const words = input.split(' ');
  let output = "";

  words.forEach(word => {
    if (word.length > 4) {
      output = output.concat(word.split('').reverse().join(''), ' ');
    } else {
      output = output.concat(word, ' ');
    }
  });

  return output.trim();
}

/**
 * Write a function called calculateTotal.
 * Given an array of numbers the function calculates a total from the array given some...peculiar rules.
 * 
 * An amount is either added to or subtracted from this total given the following conditions:
 * If the length of the array is a multiple of a number, subtract the number from the total instead.
 * If a number is a multiple of 2, add it to the total twice. 
 * If a number is a multiple of 2 AND of the length of the array, subtract it from the total twice.
 * 
 * @param {number[]} numbers
 * @returns {number} total
 */
function calculateTotal(numbers) {
  let total = 0;
  numbers.forEach(num => {
    if (numbers.length % num === 0) {
      if (num % 2 === 0) {
        total -= 2 * num;
      } else {
        total -= num;
      }
    }
    else if (num % 2 === 0) {
      total += 2 * num;
    } else {
      total += num;
    }
  });
  return total;
}

/**
 * Caesar ciphers, or shift ciphers, are one of the most basic forms of encryption. 
 * Each letter in the message is replaced by a letter some fixed number of positions down the alphabet, determined by the key.
 * For example, with a key of 3, D would become G, E would become H, etc.
 * 
 * Write a function called caesarCipher to encrypt a given string with a given key.
 * Be sure that a letter that is uppercase remains uppercase and vice versa.
 * Make sure to only shift letters - all punctuation, numbers, and spaces also remain the same.
 * Also be aware of keys greater than 26 and less than -26. There are only 26 letters in the alphabet.
 * If a key falls outside of this range loop back to the start of the alphabet, so a key of 27 yields the same result as a key of 1, and 28, 2, etc.
 * 
 * @param {number} key
 * @param {string} message
 * @returns {string}
 */
function caesarCipher(key, message) {
  const alphabet = ['A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'f', 'G', 'g', 'H', 'h', 'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', 'M', 'm', 'N', 'n', 'O', 'o', 'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's', 'T', 't', 'U', 'u', 'V', 'v', 'W', 'w', 'X', 'x', 'Y', 'y', 'Z', 'z']
  const encryptedMessage = message.split('').map((item) => {
    if (alphabet.includes(item)) {
      const index = ((alphabet.indexOf(item)) + key * 2) % 52;
      if (index < 0) {
        return alphabet[index + alphabet.length];
      }
      return alphabet[index];
    }
    else {
      return item;
    }
  });
  return encryptedMessage.join('');
}

/**
 * Write a function called orderArrivals
 * It takes an array of objects with the following properties:
 *   name
 *   estimatedTripTime (minutes)
 *   avgSpeed (miles per hour)
 *   routeDistance (miles)
 *   distanceRemaining (miles)
 * 
 * This function calculates if a train is on schedule, when it will arrive in minutes, and sorts the array in order of arrival.
 * It then returns an array of objects with the following properties, sorted from least timeRemaining to most:
 *   name
 *   isOnSchedule
 *   timeRemaining
 * 
 * @param {object[]} trains
 * @returns {object}
 */
function orderArrivals(trains) {
  let arrivalTimes = [];
  trains.forEach(train => {
    let actualTripTime = (train.routeDistance / train.avgSpeed) * 60;
    let timeRemaining = (train.distanceRemaining / train.avgSpeed) * 60;

    let isOnSchedule = actualTripTime <= train.estimatedTripTime;

    arrivalTimes.push({
      name: train.name,
      timeRemaining: timeRemaining,
      isOnSchedule: isOnSchedule,
    })
  });

  arrivalTimes.sort((a, b) => {
    return a.timeRemaining - b.timeRemaining;
  });
  return arrivalTimes;
}

/**
 * Being a wrangler for Monsters"R"Us is dangerous work. 
 * Write a function called captureMonsters to calculate whether you are able to safely capture a group of monsters before taking to the field.
 * This function should also return counts for any remaining traps, monsters, and distance for record keeping purposes.
 * 
 * Write a function called captureMonsters
 * The function takes an object with the following properties:
 *   numberOfMonsters
 *   numberOfTraps
 *   distance
 * 
 * Then returns an object with the following properties:
 *   isSafe
 *   monstersRemaining
 *   trapsRemaining
 *   distanceRemaining 
 * 
 * Distance is measured in feet.
 * Trap a monster every turn, removing one trap, and one monster.
 * If there are no monsters remaining before the distance reaches 0, the capture isSuccessful, otherwise the capture has failed.
 * 
 * The order of each turn should be:
 * Remove monster/trap if enough remain
 * Check if monsters have reached you or have all been trapped
 * Monsters move 1.5ft closer
 * Check if monsters have reached you or have all been trapped
 * 
 * @param {object} properties
 * @param {number} properties.numberOfMonsters
 * @param {number} properties.numberOfTraps
 * @param {number} properties.distance
 * @returns {object}
 */
function captureMonsters(properties) {
  while (properties.distance > 0 && properties.numberOfMonsters > 0) {
    properties.numberOfTraps--;
    properties.numberOfMonsters--;

    if (properties.numberOfMonsters === 0) {
      return {
        isSafe: true,
        trapsRemaining: properties.numberOfTraps,
        monstersRemaining: properties.numberOfMonsters,
        distanceRemaining: properties.distance,
      };
    }
    properties.distance -= 1.5;
    if (properties.distance <= 0) {
      return {
        isSafe: false,
        trapsRemaining: properties.numberOfTraps,
        monstersRemaining: properties.numberOfMonsters,
        distanceRemaining: 0,
      };
    }
  }
}

/**
 * ***CHALLENGE***
 * Write a function called characterCount
 * It takes a string and returns an object that shows how many times each character occurs in the string.
 * The object should have a key for each character in the string, and the value should be the number of times it occurs.
 * 
 * Don't make the count case-sensitive, and return all keys in lowercase.
 * Do not count any spaces.
 * 
 * Given the following string:
 * "Screensaver"
 * Your function should return:
 * {
 *   "s": 2,
 *   "c": 1,
 *   "r": 2,
 *   "e": 3,
 *   "n": 1,
 *   "a": 1,
 *   "v": 1
 * }
 * 
 * @param {string} input
 * @returns {object}
 */
function characterCount(input) {
  input = input.toLowerCase().replaceAll(' ', '');
  let output = {};
  while (input.length > 0) {
    let char = input[0];
    let count = input.split(char).length - 1;
    output[char] = count;
    input = input.replaceAll(char, '');
  }

  return output;
}
